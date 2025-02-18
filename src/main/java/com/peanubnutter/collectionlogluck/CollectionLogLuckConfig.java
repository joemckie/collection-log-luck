package com.peanubnutter.collectionlogluck;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import static com.peanubnutter.collectionlogluck.CollectionLogLuckConfig.COLLECTION_LOG_LUCK_CONFIG_GROUP;

@ConfigGroup(COLLECTION_LOG_LUCK_CONFIG_GROUP)
public interface CollectionLogLuckConfig extends Config
{
	String COLLECTION_LOG_LUCK_CONFIG_GROUP = "collectionlogluck";
	String COLLECTION_LOG_LUCK_CONFIG_VERSION_KEY = "collection_log_luck_version";

	String NUM_INVALID_BARROWS_KC_KEY = "num_invalid_barrows_kc";
	String BARROWS_BOLT_RACKS_ENABLED_KEY = "barrows_bolt_racks_enabled";
	String AVG_PERSONAL_COX_POINTS_KEY = "avg_personal_cox_points";
	String AVG_PERSONAL_COX_CM_POINTS_KEY = "avg_personal_cox_cm_points";
	String AVG_PERSONAL_TOB_POINTS_KEY = "avg_personal_tob_points";
	String AVG_PERSONAL_TOB_HM_POINTS_KEY = "avg_personal_tob_hm_points";
	String ENTRY_TOA_UNIQUE_CHANCE_KEY = "entry_toa_unique_chance";
	String REGULAR_TOA_UNIQUE_CHANCE_KEY = "regular_toa_unique_chance";
	String EXPERT_TOA_UNIQUE_CHANCE_KEY = "expert_toa_unique_chance";
	String AVG_NIGHTMARE_TEAM_SIZE_KEY = "avg_nightmare_team_size";
	String AVG_NIGHTMARE_CONTRIBUTION_KEY = "avg_nightmare_contribution";
	String AVG_NEX_CONTRIBUTION_KEY = "avg_nex_contribution";
	String AVG_HUEYCOATL_CONTRIBUTION_KEY = "avg_hueycoatl_contribution";
	String AVG_ROYAL_TITANS_CONTRIBUTION_KEY = "avg_royal_titans_contribution";
	String AVG_ZALCANO_CONTRIBUTION_KEY = "avg_zalcano_contribution";
	String AVG_ZALCANO_POINTS_KEY = "avg_zalcano_points";
	String NUM_FIRE_CAPES_SACRIFICED_KEY = "num_fire_capes_sacrificed";
	String NUM_INFERNAL_CAPES_SACRIFICED_KEY = "num_infernal_capes_sacrificed";
	String NUM_DIZANAS_QUIVERS_SACRIFICED_KEY = "num_dizanas_quivers_sacrificed";
	String NUM_ARAXXOR_DESTROYED_KEY = "num_araxxor_destroyed";
	String NUM_ROYAL_TITANS_SACRIFICED_KEY = "num_royal_titans_sacrificed";
	String AVG_CALLISTO_CONTRIBUTION_KEY = "avg_callisto_contribution";
	String AVG_VENENATIS_CONTRIBUTION_KEY = "avg_venenatis_contribution";
	String AVG_VETION_CONTRIBUTION_KEY = "avg_vetion_contribution";
	String AVG_SCURRIUS_MVP_RATE_KEY = "avg_scurrius_mvp_rate";
	String NUM_CRYSTAL_WEAPON_SEEDS_PURCHASED_KEY = "num_crystal_weapon_seeds_purchased";
	String SKOTIZO_KC_PRE_BUFF_KEY = "skotizo_kc_pre_buff";
	String KQ_KC_PRE_D_PICK_BUFF_KEY = "kq_kc_pre_d_pick_buff";
	String KBD_KC_PRE_D_PICK_BUFF_KEY = "kbd_kc_pre_d_pick_buff";
	String NIGHTMARE_KC_PRE_BUFF_KEY = "nightmare_kc_pre_buff";
	String PHOSANIS_NIGHTMARE_KC_PRE_BUFF_KEY = "phosanis_nightmare_kc_pre_buff";

	String SHOW_PLUGIN_UPDATES_KEY = "show_plugin_updates";
	String HIDE_PERSONAL_LUCK_CALCULATION_KEY = "hide_personal_luck_calculation";
	String SHOW_LUCK_TEXT_ON_COLLECTION_LOG_KEY = "show_luck_text_on_collection_log";
	String SHOW_LUCK_BACKGROUND_ON_COLLECTION_LOG_KEY = "show_luck_background_on_collection_log";
	String REPLACE_PERCENTILE_WITH_DRYCALC_NUMBER_KEY = "replace_percentile_with_drycalc_number";

	// Used in GET request to collectionlog.net. Should be up-to-date with collection log plugin updates.
	String COLLECTION_LOG_VERSION = "3.1.3";

	@ConfigSection(
		name = "Appearance",
		description = "Luck display settings across the plugin",
		position = 1
	)
	String appearanceSection = "appearance";

	@ConfigItem(
			keyName = SHOW_PLUGIN_UPDATES_KEY,
			name = "Show plugin update message",
			description = "On first login after a plugin update, show an update message in chat.",
			position = 1,
			section = appearanceSection
	)
	default boolean showPluginUpdates()
	{
		return true;
	}


	// Other players' luck will always show, for example though the !luck command, but the player may want to hide
	// their own luck because it could be unpleasant to see.
	@ConfigItem(
			keyName = HIDE_PERSONAL_LUCK_CALCULATION_KEY,
			name = "Hide personal luck",
			description = "Hide your luck stats from yourself. Others can still see your chat commands.",
			position = 2,
			section = appearanceSection
	)
	default boolean hidePersonalLuckCalculation()
	{
		return false;
	}

	@ConfigItem(
			keyName = SHOW_LUCK_TEXT_ON_COLLECTION_LOG_KEY,
			name = "Show collection log luck percentile",
			description = "Whether to show your luck percentile on the collection log page.",
			position = 3,
			section = appearanceSection
	)
	default boolean showCollectionLogOverlayText()
	{
		return true;
	}

	@ConfigItem(
			keyName = SHOW_LUCK_BACKGROUND_ON_COLLECTION_LOG_KEY,
			name = "Show collection log background colors",
			description = "Whether to show your luck percentile as a background color the collection log page.",
			position = 4,
			section = appearanceSection
	)
	default boolean showCollectionLogOverlayBackground()
	{
		return true;
	}

	@ConfigItem(
			keyName = REPLACE_PERCENTILE_WITH_DRYCALC_NUMBER_KEY,
			name = "Replace percentile with DryCalc #",
			description = "Show DryCalc number (% chance of having your # of drops or fewer) rather than percentile",
			position = 5,
			section = appearanceSection
	)
	default boolean replacePercentileWithDrycalcNumber()
	{
		return false;
	}

	// ############### Luck section ###############

	@ConfigSection(
			name = "Luck calculation",
			description = "Config options for calculation collection log luck",
			position = 2
	)
	String luckSection = "Luck calculation";

	// ############### Raids, in order, are at the top since it's likely most interesting to people. ###############

	@ConfigItem(
			keyName = AVG_PERSONAL_COX_POINTS_KEY,
			name = "CoX points per raid",
			description = "The average # of points you personally receive per Chambers of Xeric raid.",
			position = 10,
			section = luckSection
	)
	default int avgPersonalCoxPoints()
	{
		return 30_000;
	}

	@ConfigItem(
			keyName = AVG_PERSONAL_COX_CM_POINTS_KEY,
			name = "CoX CM points per raid",
			description = "The average # of points you personally receive per Chambers of Xeric Challenge Mode raid.",
			position = 11,
			section = luckSection
	)
	default int avgPersonalCoxCmPoints()
	{
		return 45_000;
	}

	@ConfigItem(
			keyName = AVG_PERSONAL_TOB_POINTS_KEY,
			name = "ToB point fraction",
			description = "The average fraction (0 to 1) of max team points you receive per Theatre of Blood raid, including MVP points.",
			position = 12,
			section = luckSection
	)
	default double avgPersonalTobPointFraction()
	{
		return 0.25;
	}

	@ConfigItem(
			keyName = AVG_PERSONAL_TOB_HM_POINTS_KEY,
			name = "ToB HM point fraction",
			description = "The average fraction (0 to 1) of max team points you receive per Theatre of Blood Hard Mode raid, including MVP points.",
			position = 13,
			section = luckSection
	)
	default double avgPersonalTobHmPointFraction()
	{
		return 0.2;
	}

	// Note: This assumes that there is no reason to ever do a raid less than 50 invocation level.
	@ConfigItem(
			keyName = ENTRY_TOA_UNIQUE_CHANCE_KEY,
			name = "Entry ToA Unique Chance",
			description = "Use a plugin/calc to estimate your chance (0 to 1) of a unique for your typical raid setup. Defaults to 50 invocation level.",
			position = 14,
			section = luckSection
	)
	default double entryToaUniqueChance()
	{
		return 0.0076;
	}

	@ConfigItem(
			keyName = REGULAR_TOA_UNIQUE_CHANCE_KEY,
			name = "Regular ToA Unique Chance",
			description = "Use a plugin/calc to estimate your chance (0 to 1) of a unique for your typical raid setup. Defaults to 150 invocation level.",
			position = 15,
			section = luckSection
	)
	default double regularToaUniqueChance()
	{
		return 0.0202;
	}

	@ConfigItem(
			keyName = EXPERT_TOA_UNIQUE_CHANCE_KEY,
			name = "Expert ToA Unique Chance",
			description = "Use a plugin/calc to estimate your chance (0 to 1) of a unique for your typical raid setup. Defaults to 300 invocation level.",
			position = 16,
			section = luckSection
	)
	default double expertToaUniqueChance()
	{
		return 0.0440;
	}

	// ############### Team based bosses with contribution (% damage dealt and/or MVP mechanic) ###############

	@ConfigItem(
			keyName = AVG_NIGHTMARE_TEAM_SIZE_KEY,
			name = "Nightmare team size",
			description = "Average team size when killing The Nightmare of Ashihama. Decimals can be used.",
			position = 20,
			section = luckSection
	)
	default double avgNightmareTeamSize() {
		return 5;
	}

	@ConfigItem(
			keyName = AVG_NIGHTMARE_CONTRIBUTION_KEY,
			name = "Nightmare contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing The Nightmare of Ashihama." +
					" This should include MVP bonuses, so multiply by 1.05 if always MVP, or less accordingly.",
			position = 21,
			section = luckSection
	)
	default double avgNightmareContribution() {
		// average MVP rate of 20% with an average contribution on a 5-man team
		return 0.202;
	}

	@ConfigItem(
			keyName = AVG_NEX_CONTRIBUTION_KEY,
			name = "Nex contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing Nex." +
					" This should include MVP bonuses, so multiply by 1.1 if always MVP, or less accordingly.",
			position = 22,
			section = luckSection
	)
	default double avgNexContribution() {
		// average MVP rate of 20% with an average contribution on a 5-man team
		return 0.204;
	}

	@ConfigItem(
			keyName = AVG_HUEYCOATL_CONTRIBUTION_KEY,
			name = "Hueycoatl contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing The Hueycoatl." +
					" This should include MVP bonuses, so multiply by 1.1 if always MVP, or less accordingly.",
			position = 23,
			section = luckSection
	)
	default double avgHueycoatlContribution() {
		// average MVP rate of 33% with an average contribution on a 3-man team
		return 0.367;
	}

	@ConfigItem(
			keyName = AVG_ROYAL_TITANS_CONTRIBUTION_KEY,
			name = "Royal Titans contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing the Royal Titans. Set to 1 if you only solo.",
			position = 24,
			section = luckSection
	)
	default double avgRoyalTitansContribution() {
		// Assume duo by default, with average contribution
		return 0.5;
	}

	@ConfigItem(
			keyName = AVG_CALLISTO_CONTRIBUTION_KEY,
			name = "Callisto contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing Callisto." +
					" Set to 0.1 if team size >= 10, or 1 if soloing.",
			position = 25,
			section = luckSection
	)
	default double avgCallistoContribution() {
		return 0.2;
	}

	@ConfigItem(
			keyName = AVG_VENENATIS_CONTRIBUTION_KEY,
			name = "Venenatis contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing Venenatis." +
					" Set to 0.1 if team size >= 10, or 1 if soloing.",
			position = 26,
			section = luckSection
	)
	default double avgVenenatisContribution() {
		return 0.5;
	}

	@ConfigItem(
			keyName = AVG_VETION_CONTRIBUTION_KEY,
			name = "Vet'ion contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing Vet'ion." +
					" Set to 0.1 if team size >= 10, or 1 if soloing.",
			position = 27,
			section = luckSection
	)
	default double avgVetionContribution() {
		return 0.5;
	}

	@ConfigItem(
			keyName = AVG_SCURRIUS_MVP_RATE_KEY,
			name = "Scurrius MVP rate",
			description = "Fraction (0 to 1) of the time you are MVP while fighting Scurrius. Set to 1 if you always solo.",
			position = 28,
			section = luckSection
	)
	default double avgScurriusMvpRate() {
		// Solo is most efficient
		return 1;
	}

	@ConfigItem(
			keyName = AVG_ZALCANO_CONTRIBUTION_KEY,
			name = "Zalcano contribution",
			description = "Avg. fraction (0 to 1) of contribution to killing Zalcano, taking into account team size.",
			position = 29,
			section = luckSection
	)
	default double avgZalcanoContribution() {
		// 4 man is most efficient
		return 0.25;
	}

	@ConfigItem(
			keyName = AVG_ZALCANO_POINTS_KEY,
			name = "Zalcano points",
			description = "Your average number of points per Zalcano kill. See wiki for more info.",
			position = 30,
			section = luckSection
	)
	default int avgZalcanoPoints() {
		// According to Zalcano community, 210 to 350 points is normal in efficient 4-man
		return 300;
	}

	// ############### Misc minigames and minor bosses. ###############

	// Completing Barrows without killing all 6 brothers, for example if rapidly resetting to finish Barrows combat
	// achievements, drastically reduces the chance of receiving unique loot. The player can configure an approximate
	// number of Barrows KC they have wasted, including summing fractional less-than-6-brother-kills, to make the luck
	// calculation more accurate. This is completely optional, and being exact is not really necessary.
	@ConfigItem(
			keyName = NUM_INVALID_BARROWS_KC_KEY,
			name = "# Barrows KC wasted",
			description = "The effective number of Barrows KC wasted by killing < 6 brothers. 4-5 brothers killed ~= 0.5 KC wasted.",
			position = 31,
			section = luckSection
	)
	default int numInvalidBarrowsKc()
	{
		return 0;
	}

	@ConfigItem(
			keyName = BARROWS_BOLT_RACKS_ENABLED_KEY,
			name = "Bolt racks enabled",
			description = "Whether or not you try to get enough points at Barrows to receive bolt racks.",
			position = 32,
			section = luckSection
	)
	default boolean barrowsBoltRacksEnabled()
	{
		return false;
	}

	// ############### Manually purchased or sacrificed items. Requires regular updates by the user. ###############

	@ConfigItem(
			keyName = NUM_FIRE_CAPES_SACRIFICED_KEY,
			name = "# Fire capes sacrificed",
			description = "The number of fire capes sacrificed for a chance at TzRek-Jad.",
			position = 40,
			section = luckSection
	)
	default int numFireCapesSacrificed() {
		return 0;
	}

	@ConfigItem(
			keyName = NUM_INFERNAL_CAPES_SACRIFICED_KEY,
			name = "# Infernal capes sacrificed",
			description = "The number of infernal capes sacrificed for a chance at Jal-nib-rek.",
			position = 41,
			section = luckSection
	)
	default int numInfernalCapesSacrificed() {
		return 0;
	}

	@ConfigItem(
			keyName = NUM_DIZANAS_QUIVERS_SACRIFICED_KEY,
			name = "# Dizana's Quivers sacrificed",
			description = "The number of Dizana's Quivers sacrificed for a chance at Smol Heredit.",
			position = 42,
			section = luckSection
	)
	default int numDizanasQuiversSacrificed() {
		return 0;
	}

	@ConfigItem(
			keyName = NUM_ARAXXOR_DESTROYED_KEY,
			name = "# Araxxor destroyed",
			description = "The number of Araxxor corpses destroyed for a chance at Nid.",
			position = 43,
			section = luckSection
	)
	default int numAraxxorDestroyed() {
		return 0;
	}

	@ConfigItem(
			keyName = NUM_ROYAL_TITANS_SACRIFICED_KEY,
			name = "# Royal Titans sacrificed",
			description = "The number of Royal Titans corpses sacrificed for a chance at Bran.",
			position = 24,
			section = luckSection
	)
	default int numRoyalTitansSacrificed() {
		return 0;
	}

	// Purchasing crystal weapon seeds prevents calculating how many the player has received through the Gauntlet.
	// The calculation can be corrected if the player inputs the number purchased from the shop.
	@ConfigItem(
			keyName = NUM_CRYSTAL_WEAPON_SEEDS_PURCHASED_KEY,
			name = "# Crystal weapon seeds bought",
			description = "The number of crystal weapon seeds you bought from the Last Man Standing shop.",
			position = 46,
			section = luckSection
	)
	default int numCrystalWeaponSeedsPurchased()
	{
		return 0;
	}

	// ############### Settings based on historical drop rate changes ###############

	@ConfigItem(
			keyName = SKOTIZO_KC_PRE_BUFF_KEY,
			name = "Skotizo KC pre-buff",
			description = "# of Skotizo kills before the Jar of darkness drop rate buff",
			position = 50,
			section = luckSection
	)
	default int skotizoKcPreBuff() {
		return 0;
	}

	@ConfigItem(
			keyName = KQ_KC_PRE_D_PICK_BUFF_KEY,
			name = "KQ KC pre- d pick buff",
			description = "# of Kalphite Queen kills before the Dragon Pickaxe was added to the drop table.",
			position = 51,
			section = luckSection
	)
	default int kqKcPreDPickBuff() {
		return 0;
	}

	@ConfigItem(
			keyName = KBD_KC_PRE_D_PICK_BUFF_KEY,
			name = "KBD KC pre- d pick buff",
			description = "# of King Black Dragon kills before the Dragon Pickaxe drop rate buff.",
			position = 52,
			section = luckSection
	)
	default int kbdKcPreDPickBuff() {
		return 0;
	}

	@ConfigItem(
			keyName = NIGHTMARE_KC_PRE_BUFF_KEY,
			name = "Nightmare KC pre-buff",
			description = "# of Nightmare kills before the drop rate buffs.",
			position = 53,
			section = luckSection
	)
	default int nightmareKcPreBuff() {
		return 0;
	}

	@ConfigItem(
			keyName = PHOSANIS_NIGHTMARE_KC_PRE_BUFF_KEY,
			name = "Phosani's Nightmare KC pre-buff",
			description = "# of Phosani's Nightmare kills before the drop rate buffs.",
			position = 54,
			section = luckSection
	)
	default int phosanisNightmareKcPreBuff() {
		return 0;
	}

	// ############### Hidden settings ###############

	@ConfigSection(
			name = "Debug",
			description = "Internal plugin state and hidden settings",
			position = 99,
			closedByDefault = true
	)
	String debugSection = "debug";

	@ConfigItem(
			keyName = COLLECTION_LOG_LUCK_CONFIG_VERSION_KEY,
			name = "Collection Log Luck plugin version",
			description = "Version of the plugin for update message",
			section = debugSection,
			position = 1
	) default String getVersion() { return ""; }

}
