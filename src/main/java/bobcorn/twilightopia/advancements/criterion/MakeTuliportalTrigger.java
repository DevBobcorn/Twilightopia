package bobcorn.twilightopia.advancements.criterion;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import bobcorn.twilightopia.TwilightopiaMod;

import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class MakeTuliportalTrigger implements ICriterionTrigger<MakeTuliportalTrigger.Instance> {
   private static final ResourceLocation ID = new ResourceLocation(TwilightopiaMod.MODID, "make_tuliportal");
   private final Map<PlayerAdvancements, MakeTuliportalTrigger.Listeners> listeners = Maps.newHashMap();

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance> listener) {
      MakeTuliportalTrigger.Listeners portaltriggerlisteners = this.listeners.get(playerAdvancementsIn);
      if (portaltriggerlisteners == null) {
         portaltriggerlisteners = new MakeTuliportalTrigger.Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, portaltriggerlisteners);
      }

      portaltriggerlisteners.addListener(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance> listener) {
      MakeTuliportalTrigger.Listeners portaltriggerlisteners = this.listeners.get(playerAdvancementsIn);
      if (portaltriggerlisteners != null) {
         portaltriggerlisteners.removeListener(listener);
         if (portaltriggerlisteners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }
   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public void trigger(ServerPlayerEntity player) {
      MakeTuliportalTrigger.Listeners portaltriggerlisteners = this.listeners.get(player.getAdvancements());
      if (portaltriggerlisteners != null) {
         portaltriggerlisteners.trigger();
      }
   }

   public static class Instance extends CriterionInstance {
      public Instance() {
         super(MakeTuliportalTrigger.ID);
      }
   }

   static class Listeners {
      private final PlayerAdvancements playerAdvancements;
      private final Set<ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance>> listeners = Sets.newHashSet();

      public Listeners(PlayerAdvancements playerAdvancementsIn) {
         this.playerAdvancements = playerAdvancementsIn;
      }

      public boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      public void addListener(ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance> listener) {
         this.listeners.add(listener);
      }

      public void removeListener(ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance> listener) {
         this.listeners.remove(listener);
      }

      public void trigger() {
    	  for(ICriterionTrigger.Listener<MakeTuliportalTrigger.Instance> listener : Lists.newArrayList(this.listeners)) {
              listener.grantCriterion(this.playerAdvancements);
           }
      }
   }

	@Override
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		return new MakeTuliportalTrigger.Instance();
	}
}