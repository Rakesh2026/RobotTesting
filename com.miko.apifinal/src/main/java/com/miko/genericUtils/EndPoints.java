package com.miko.genericUtils;
/**
 * Interface to store all the endpoints given in the application
 * @author Rakesh
 *
 */
public interface EndPoints {
	
	String logInOrderID="/login";
	String logInOrderID_Invalid="/Login";
	String VerifyOTP="/verify-otp";
	String VerifyOTP_Invalid="/Verify-otp";
	String Register="/auth/register";
	String Register_Invalid="/Auth/Register";
	String Config="/config";
	String Config_Invalid="/Config";
	String DashboardBanner="/config/dashboard";
	String DashboardBanner_Invalid="/Config/Dashboard";
	String SplashScreenConfig="/config/splash-screen";
	String SplashScreenConfig_Invalid="/Config/Splash-screen";
	String KeysConfig="/profile/config";
	String KeysConfig_Invalid="/Profile/Config";
	String DashboardHome="/config/dashboard-home";
	String DashboardHome_Invalid="/Config/Dashboard-home";
	String MaxPageHomecarousel="/config/max-home/carousel";
	String MaxPageHomecarousel_Invalid="/Config/Max-home/Carousel";
	String UnsubscribeReason="/config/unsubscribe-reasons";
	String UnsubscribeReason_Invalid="/Config/Unsubscribe-reasons";
	String MaxPlanHomePageBanner="/config/plan-home/carousel";
	String MaxPlanHomePageBanner_Invalid="/Config/Plan-home/Carousel";
	String GetListOfLanguages="/languages";
	String GetListOfLanguages_Invalid="/Languages";
	String GetKeyValuesOfaLanguages="/languages/{language_id}";
	String GetKeyValuesOfaLanguages_Invalid="/Languages/{language_id}";
	String GetOrderDetails="/order/:order_id";
	String GetOrderDetails_Invalid="/Order/:order_id";
	String GetCustomerAuthenticationMethodByOrderId="/Order/{order_id}";
	String GetCustomerAuthenticationMethodByOrderId_Invalid="/order/{order_id}";
	String GetProfileForCustomer="/my-profile";
	String GetProfileForCustomer_Invalid="/My-profile";
	String GetBotDetails="/miko3/does-exists";
	String GetBotDetails_Invalid="/Miko3/does-exists";
	String PairWithBot="/miko3/{bot_code}/pair";
	String PairWithBot_Invalid="/Miko3/{bot_code}/pair";
	String GetListOfBotPairedWithUser="/user_bot";
	String GetListOfBotPairedWithUser_Invalid="/User_bot";
	String SwitchBot="/user/bot";
	String SwitchBot_Invalid="/User/bot";
	String UnlinkBot="/user/bot";
	String ProxyApi="";
	String ProxyApi_Invalid="";
	String GetAllTalents="/talents";
	String GetAllTalents_Invalid="/Talents";
	String GetOneTalent="/talents/{id}";
	String GetOneTalent_Invalid="/Talents/{id}";
	String GetAllTidbits="/tidbits";
	String GetAllTidbits_Invalid="/Tidbits";
	String GetOneTidbit="/tidbits/{id}";
	String GetOneTidbit_Invalid="/Tidbits/{id}";
	String GetAllTTM="/talk-to-miko";
	String GetAllTTM_Invalid="/Talk-to-miko";
	String GetOneTTM="/talk-to-miko/:id";
	String GetOneTTM_Invalid="/Talk-to-miko/{id}";
	String GetMaxHomePage="/home";
	String GetMaxHomePage_Invalid="/Home";
	String GetListOfMaxSubscriptions="/max-subscription";
	String GetListOfMaxSubscriptions_Invalid="/Max-subscription";
	String CreateSubscription="/max-subscription/user";
	String CreateSubscription_Invalid="/Max-subscription/user";
	String GetPlanDetails="/plan-detail";
	String GetPlanDetails_Invalid="/Plan-detail";
	String GetCoupons="/coupon/list";
	String GetCoupons_Invalid="/Coupon/list";
	String GetCallHistory="/call_history/{bot_id}";
	String GetCallHistory_Invalid="/Call_history/{bot_id}";
	String CreateCallHistory="/call_history";
	String CreateCallHistory_Invalid="/Call_history";
	String GetLocation="/ip-geolocate";
	String GetLocation_Invalid="/Ip-geolocate";
	String SetCoppaStatusforaUser="/user/coppa";
	String SetCoppaStatusforaUser_Invalid="/User/coppa";
	String SearchCity="/city";
	String SearchCity_Invalid="/City";
	String GetTimezoneOfTheCity="/timezone/city";
	String GetTimezoneOfTheCity_Invalid="/Timezone/city";
	String SendPushNotification="/push-notification/send";
	String SendPushNotification_Invalid="/Push-notification/send";
	String SendManualNotification="/push-notification/send/manual";
	String SendManualNotification_Invalid="/Push-notification/send/manual";
	String SendSilentNotification="/push-notification/send/silent";
	String SendSilentNotification_Invalid="/Push-notification/send/silent";
	
	
}
