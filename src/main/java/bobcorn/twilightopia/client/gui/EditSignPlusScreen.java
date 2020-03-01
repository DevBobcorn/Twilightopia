package bobcorn.twilightopia.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.StandingSignPlusBlock;
import bobcorn.twilightopia.blocks.WallSignPlusBlock;
import bobcorn.twilightopia.network.C2SUpdateSignPlus;
import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.fonts.TextInputUtil;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EditSignPlusScreen extends Screen {
   private final SignPlusTileEntity tileSign;
   private int updateCounter;
   private int editLine;
   private TextInputUtil field_214267_d;

   public EditSignPlusScreen(SignPlusTileEntity teSign) {
      super(new TranslationTextComponent("sign.edit"));
      this.tileSign = teSign;
   }

   protected void init() {
      this.minecraft.keyboardListener.enableRepeatEvents(true);
      this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120, 200, 20, I18n.format("gui.done"), (p_214266_1_) -> {
         this.close();
      }));
      this.tileSign.setEditable(false);
      this.field_214267_d = new TextInputUtil(this.minecraft, () -> {
         return this.tileSign.getText(this.editLine).getString();
      }, (p_214265_1_) -> {
         this.tileSign.setText(this.editLine, new StringTextComponent(p_214265_1_));
      }, 90);
   }

   public void removed() {
      this.minecraft.keyboardListener.enableRepeatEvents(false);
      /*
      ClientPlayNetHandler clientplaynethandler = this.minecraft.getConnection();
      if (clientplaynethandler != null) {
         clientplaynethandler.sendPacket(new CUpdateSignPlusPacket(this.tileSign.getPos(), this.tileSign.getText(0), this.tileSign.getText(1), this.tileSign.getText(2), this.tileSign.getText(3)));
      }
      */
      //System.out.println("Send C2S, Update Sign+");
      TwilightopiaMod.CHANNEL.sendToServer(new C2SUpdateSignPlus(this.tileSign.getPos(), this.tileSign.getText(0).getString(), this.tileSign.getText(1).getString(), this.tileSign.getText(2).getString(), this.tileSign.getText(3).getString()));
      this.tileSign.setEditable(true);
   }

   public void tick() {
      ++this.updateCounter;
      if (!this.tileSign.getType().isValidBlock(this.tileSign.getBlockState().getBlock())) {
         this.close();
      }

   }

   private void close() {
      this.tileSign.markDirty();
      this.minecraft.displayGuiScreen((Screen)null);
   }

   public boolean charTyped(char p_charTyped_1_, int p_charTyped_2_) {
      this.field_214267_d.func_216894_a(p_charTyped_1_);
      return true;
   }

   public void onClose() {
      this.close();
   }

   public boolean keyPressed(int p_keyPressed_1_, int p_keyPressed_2_, int p_keyPressed_3_) {
      if (p_keyPressed_1_ == 265) {
         this.editLine = this.editLine - 1 & 3;
         this.field_214267_d.func_216899_b();
         return true;
      } else if (p_keyPressed_1_ != 264 && p_keyPressed_1_ != 257 && p_keyPressed_1_ != 335) {
         return this.field_214267_d.func_216897_a(p_keyPressed_1_) ? true : super.keyPressed(p_keyPressed_1_, p_keyPressed_2_, p_keyPressed_3_);
      } else {
         this.editLine = this.editLine + 1 & 3;
         this.field_214267_d.func_216899_b();
         return true;
      }
   }

   public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
      this.renderBackground();
      this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 40, 16777215);
      GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.translatef((float)(this.width / 2), 0.0F, 50.0F);
      GlStateManager.scalef(-93.75F, -93.75F, -93.75F);
      GlStateManager.rotatef(180.0F, 0.0F, 1.0F, 0.0F);
      BlockState blockstate = this.tileSign.getBlockState();
      float f1;
      if (blockstate.getBlock() instanceof StandingSignPlusBlock) {
         f1 = (float)(blockstate.get(StandingSignPlusBlock.ROTATION) * 360) / 16.0F;
      } else {
         f1 = blockstate.get(WallSignPlusBlock.FACING).getHorizontalAngle();
      }

      GlStateManager.rotatef(f1, 0.0F, 1.0F, 0.0F);
      GlStateManager.translatef(0.0F, -1.0625F, 0.0F);
      this.tileSign.func_214062_a(this.editLine, this.field_214267_d.func_216896_c(), this.field_214267_d.func_216898_d(), this.updateCounter / 6 % 2 == 0);
      TileEntityRendererDispatcher.instance.render(this.tileSign, -0.5D, -0.75D, -0.5D, 0.0F);
      this.tileSign.func_214063_g();
      GlStateManager.popMatrix();
      super.render(p_render_1_, p_render_2_, p_render_3_);
   }
}