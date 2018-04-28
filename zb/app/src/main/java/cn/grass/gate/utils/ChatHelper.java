//package cn.grass.palmTax.utils;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.content.LocalBroadcastManager;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.hyphenate.EMConnectionListener;
//import com.hyphenate.EMContactListener;
//import com.hyphenate.EMError;
//import com.hyphenate.EMGroupChangeListener;
//import com.hyphenate.EMMessageListener;
//import com.hyphenate.EMValueCallBack;
//import com.hyphenate.chat.EMChatRoom;
//import com.hyphenate.chat.EMClient;
//import com.hyphenate.chat.EMCmdMessageBody;
//import com.hyphenate.chat.EMConversation;
//import com.hyphenate.chat.EMGroup;
//import com.hyphenate.chat.EMMessage;
//import com.hyphenate.chat.EMOptions;
//import com.hyphenate.chat.EMTextMessageBody;
//import com.hyphenate.easeui.controller.EaseUI;
//import com.hyphenate.easeui.domain.EaseUser;
//import com.hyphenate.easeui.model.EaseAtMessageHelper;
//import com.hyphenate.easeui.model.EaseNotifier;
//import com.hyphenate.easeui.utils.EaseCommonUtils;
//import com.hyphenate.util.EMLog;
//import com.weedys.weedlibrary.utils.FileUtil;
//import com.weedys.weedlibrary.utils.LogUtil;
//
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import cn.grass.palmTax.GlobalApp;
//import cn.grass.palmTax.GrassApp;
//import cn.grass.palmTax.MainActivity;
//import cn.grass.palmTax.R;
//
//import cn.grass.palmTax.data.PrefManager;
//import cn.grass.palmTax.http.Api;
//import cn.grass.palmTax.http.message.AccountEvent;
//import cn.grass.palmTax.http.message.ChatEvent;
//import cn.grass.palmTax.http.message.DataEvent;
//
//
///**
// * Created by weedys on 16/7/27.
// */
//public class ChatHelper {
//
//    public static interface DataSyncListener {
//        /**
//         * sync complete
//         *
//         * @param success true：data sync successful，false: failed to sync data
//         */
//        public void onSyncComplete(boolean success);
//    }
//
//    protected static final String TAG = "ChatHelper";
//
//    private EaseUI easeUI;
//
//    /**
//     * EMEventListener
//     */
//    protected EMMessageListener messageListener = null;
//
//    private Map<String, EaseUser> contactList;
//
//    private Map<String, RobotUser> robotList;
//
//    private LocalBroadcastManager broadcastManager;
////    private UserProfileManager userProManager;
//
//    /**
//     * sync groups status listener
//     */
//    private List<DataSyncListener> syncGroupsListeners;
//    /**
//     * sync contacts status listener
//     */
//    private List<DataSyncListener> syncContactsListeners;
//
//
//    private boolean isSyncingGroupsWithServer = false;
//    private boolean isSyncingContactsWithServer = false;
//    private boolean isGroupsSyncedWithServer = false;
//    private boolean isContactsSyncedWithServer = false;
//
//
//    private boolean isGroupAndContactListenerRegisted;
//
//    private String username;
//    private static ChatHelper instance = null;
//
//    public synchronized static ChatHelper getInstance() {
//        if (instance == null) {
//            instance = new ChatHelper();
//        }
//        return instance;
//    }
//
//
//    /**
//     * init helper
//     *
//     * @param context
//     * application context
//     */
//    Context appContext;
//
//    public void init(Context context) {
//        EMOptions options = initChatOptions();
//        //use default options if options is null
//        if (EaseUI.getInstance().init(context, options)) {
//            appContext = context;
//
//            //debug mode, you'd better set it to false, if you want release your App officially.
//            EMClient.getInstance().setDebugMode(true);
//            //get easeui instance
//            easeUI = EaseUI.getInstance();
//            //to set user's profile and avatar
////            initChatOptions();
//            initProvider();
//            //initialize preference manager
//            PreferenceManager.init(context);
//            //initialize profile manager
//            setGlobalListeners();
//            broadcastManager = LocalBroadcastManager.getInstance(appContext);
//        }
//    }
//
//    private void initProvider() {
//
//        ChatHelper.getInstance().getEaseUI().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
//
//            @Override
//            public EaseUser getUser(String username) {
//                EaseUser user = null;
//                LogUtil.show("userinfo:" + username);
//                user = ChatUserDb.getUser(username);
//                if (user != null) {
//                    EaseCommonUtils.setUserInitialLetter(user);
//                    LogUtil.show("nick:" + user.nick);
//                } else {
//                }
//                return user;
////                return getUserInfo(username);
//            }
//        });
//        easeUI.setSettingsProvider(new EaseUI.EaseSettingsProvider() {
//            @Override
//            public boolean isMsgNotifyAllowed(EMMessage message) {
//                return true;
//            }
//
//            @Override
//            public boolean isMsgSoundAllowed(EMMessage message) {
//                return true;
//            }
//
//            @Override
//            public boolean isMsgVibrateAllowed(EMMessage message) {
//                return true;
//            }
//
//            @Override
//            public boolean isSpeakerOpened() {
//                return true;
//            }
//        });
//        if (easeUI.getNotifier() != null)
//            easeUI.getNotifier().setNotificationInfoProvider(new EaseNotifier.EaseNotificationInfoProvider() {
//
//                @Override
//                public String getTitle(EMMessage message) {
//                    //you can update title here
//                    return "来消息了";
//                }
//
//                @Override
//                public int getSmallIcon(EMMessage message) {
//                    //you can update icon here
//                    return R.mipmap.icon_logo;
//                }
//
//                @Override
//                public String getDisplayedText(EMMessage message) {
//                    // be used on notification bar, different text according the message type.
//                    String ticker = EaseCommonUtils.getMessageDigest(message, GrassApp.getInstance());
//                    if (message.getType() == EMMessage.Type.TXT) {
//                        ticker = ticker.replaceAll("\\[.{2,3}\\]", "[表情]");
//                    }
//                    EaseUser user = ChatUserDb.getUser(message.getFrom());
//                    if (user != null) {
//                        if (EaseAtMessageHelper.get().isAtMeMsg(message)) {
//                            return String.format("%s有一条信息", user.getNick());
//                        }
//                        return user.getNick() + ": " + ticker;
//                    } else {
//                        if (EaseAtMessageHelper.get().isAtMeMsg(message)) {
//                            return String.format("%s有一条信息", message.getFrom());
//                        }
//                        return message.getFrom() + ": " + ticker;
//                    }
//                }
//
//                @Override
//                public String getLatestText(EMMessage message, int fromUsersNum, int messageNum) {
//                    // here you can customize the text.
//                    // return fromUsersNum + "contacts send " + messageNum + "messages to you";
//                    return "点击显示更多";
//                }
//
//                @Override
//                public Intent getLaunchIntent(EMMessage message) {
//                    // you can set what activity you want display when user click the notification
//                    Intent intent = new Intent(GrassApp.getInstance(), ChatActivity.class);
//                    EMMessage.ChatType chatType = message.getChatType();
//                    if (chatType == EMMessage.ChatType.Chat) { // single chat message
//                        intent.putExtra("userId", message.getFrom());
//                        intent.putExtra("chatType", GrassConstant.CHATTYPE_SINGLE);
//                    } else { // group chat message
//                        // message.getTo() is the group id
//                        intent.putExtra("userId", message.getTo());
//                        if (chatType == EMMessage.ChatType.GroupChat) {
//                            intent.putExtra("chatType", GrassConstant.CHATTYPE_GROUP);
//                        } else {
//                            intent.putExtra("chatType", GrassConstant.CHATTYPE_CHATROOM);
//                        }
//
//                    }
////                }
//                    return intent;
//                }
//            });
//
//
//    }
//
//    public EaseUI getEaseUI() {
//        return easeUI;
//    }
//
//    private EMOptions initChatOptions() {
//        Log.d(TAG, "init HuanXin Options");
//
//        EMOptions options = new EMOptions();
//        options.setAutoLogin(true);
//        // set if accept the invitation automatically
//        options.setAcceptInvitationAlways(false);
//        // set if you need read ack
//        options.setRequireAck(true);
//        // set if you need delivery ack
//        options.setRequireDeliveryAck(false);
//
////          options.allowChatroomOwnerLeave(true);
////        options.setDeleteMessagesAsExitGroup(true);
////        options.setAutoAcceptGroupInvitation(true);
//        return options;
//    }
//
//    /**
//     * if ever logged in
//     *
//     * @return
//     */
//    public boolean isLoggedIn() {
//        return EMClient.getInstance().isLoggedInBefore();
//    }
//
//    public void saveToLocal(String json) {
//        FileUtil.saveFile(GlobalApp.DATA_CACHE_DIR + "/contact.json", json);
//    }
//
//    public ArrayList<com.hyphenate.easeui.domain.EaseUser> getContactList() {
//        String fs = FileUtil.readFile(GlobalApp.DATA_CACHE_DIR + "/contact.json");
//        return parseContactJson(fs);
//    }
//
//    public ArrayList<EaseUser> parseContactJson(String fs) {
//        if (!TextUtils.isEmpty(fs)) {
//            ArrayList<EaseUser> userInfos = null;
//            try {
//                JSONObject json = new JSONObject(fs);
//                if (json != null) {
//                    userInfos = new ArrayList<EaseUser>();
//                    JSONArray jarrGroup = json.optJSONArray("groups");
//                    if (jarrGroup != null || jarrGroup.length() > 0) {
//
//                        for (int i = 0; i < jarrGroup.length(); i++) {
//                            JSONObject gobj = jarrGroup.optJSONObject(i);
//                            String gid = gobj.optString("groupId");
//                            EaseUser chatInfo = new EaseUser(gid);
//
//                            chatInfo.username = gobj.optString("groupId");
//                            chatInfo.nick = gobj.optString("name");
//                            String avatar = gobj.optString("avator");
//                            if (!TextUtils.isEmpty(avatar)) {
//                                chatInfo.avatar = Api.HOST + avatar;
//                            } else {
//
//                            }
//                            chatInfo.admin = gobj.optString("admin");
////                            chatInfo.avatar = gobj.optString("avator");
//                            chatInfo.sign = gobj.optString("intro");
//                            chatInfo.uid = gobj.optString("groupId");
//                            chatInfo.role = EaseUser.ROLE_PARENT;
//                            chatInfo.type = EaseUser.TYPE_GROUP;
//                            chatInfo.person = gobj.optInt("groupNum");
//                            EaseCommonUtils.setUserInitialLetter(chatInfo);
//                            userInfos.add(chatInfo);
//                        }
//                    }
//
//                    JSONArray jarr = json.optJSONArray("users");
//                    if (jarr != null || jarr.length() > 0) {
//                        if (userInfos == null)
//                            userInfos = new ArrayList<EaseUser>();
//                        for (int i = 0; i < jarr.length(); i++) {
//
//                            JSONObject obj = jarr.optJSONObject(i);
//                            String im = obj.optString("imid");
//                            EaseUser chatInfo = new EaseUser(im);
//                            chatInfo.nick = obj.optString("nickname");
//                            String avatar = obj.optString("avator");
//                            if (!TextUtils.isEmpty(avatar)) {
//                                chatInfo.avatar = Api.HOST + avatar;
//                            }
//
//                            chatInfo.sign = obj.optString("signtrue");
//                            chatInfo.mphone = obj.optString("phone");
//                            chatInfo.username = im;
//                            chatInfo.uid = im;
//                            chatInfo.type = EaseUser.TYPE_PERSON;
//                            chatInfo.release = EaseUser.RELEASE_FRIEND;
//                            chatInfo.gender = obj.optInt("gender", 0);
//                            chatInfo.role = obj.optInt("uType", EaseUser.ROLE_PARENT);
//                            chatInfo.userId = obj.optString("userId");
//                            EaseCommonUtils.setUserInitialLetter(chatInfo);
//                            userInfos.add(chatInfo);
//                        }
//
//                    }
//                    return userInfos;
//                }
//            } catch (Exception e) {
//
//            }
//        }
//        return null;
//    }
//
//    public Map<String, EaseUser> getEaseUser(ArrayList<EaseUser> us) {
//        Map<String, EaseUser> map = new HashMap<>();
//        if (us == null) {
//            return null;
//        }
//
//        for (int i = 0; i < us.size(); i++) {
//            EaseUser u = us.get(i);
//            EaseCommonUtils.setUserInitialLetter(u);
//            map.put(us.get(i).username, u);
//        }
//        return map;
//    }
//
//    /**
//     * set global listener
//     */
//    EMConnectionListener connectionListener = null;
//
//    protected void setGlobalListeners() {
//        // create the global connection listener
//        connectionListener = new EMConnectionListener() {
//            @Override
//            public void onDisconnected(int error) {
//                //做重连
//                if (GrassApp.getInstance().isLogin()) {
//                    if (error == EMError.USER_NOT_LOGIN) {
//                        Log.i("tag", "未登录");
//                        String un = GrassApp.getInstance().getUserInfo().account;
//                        String up = GrassApp.getInstance().getUserInfo().pwd;
//                        LoginPresenter p = new LoginPresenter(null);
//                        p.LoginChat(un, up);
//                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
////                        EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_RELOGIN_ACCOUNT));
//                        Log.i("tag", "被踢了");
//
////                        MainActivity.startMainActivity(GrassApp.getInstance(),MainActivity.TAG_HOME);
//                        multUserLogin(GrassApp.getInstance());
//                        EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_LOGIN_MULT_ACCOUNT));
////                        ToastUtil.shortShow("该账号在其他设备登录，请重新登录！");
//                    }
//                } else {
//                    EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_RELOGIN_ACCOUNT));
//                }
//
//            }
//
//            @Override
//            public void onConnected() {
//                // in case group and contact were already synced, we supposed to notify sdk we are ready to receive the events
//
//            }
//        };
//        //register connection listener
//        EMClient.getInstance().addConnectionListener(connectionListener);
//        registerGroupAndContactListener();
//        //register message event listener
//        registerMessageListener();
//    }
//
//    private void multUserLogin(Context c) {
//        if (GrassApp.getInstance().isLogin()) {
//            RoleInfo role = GrassApp.getInstance().getUserInfo().getCurrentUserRole();
//            if (role != null) {
//                switch (role.roleCode) {
//                    case RoleInfo.ROLE_MANAGER:
//                        ManageMainActivity.startManagerActivity(c);
//                        break;
//                    case RoleInfo.ROLE_PARENT:
//                        MainActivity.startMainActivity(c);
//                        break;
//                    case RoleInfo.ROLE_TEACHER:
//                        TeacherMainActivity.startTeacherActivity(c);
//                        break;
//                    case RoleInfo.ROLE_WORKER:
//                        WorkMainActivity.startWorkActivity(c);
//                        break;
//                    default:
//                        MainActivity.startMainActivity(c);
//                        break;
//                }
//            }
//        }
//    }
//
//    /**
//     * register group and contact listener, you need register when login
//     */
//    public void registerGroupAndContactListener() {
//        if (!isGroupAndContactListenerRegisted) {
//            EMClient.getInstance().groupManager().addGroupChangeListener(new MyGroupChangeListener());
//            EMClient.getInstance().contactManager().setContactListener(new MyContactListener());
//            isGroupAndContactListenerRegisted = true;
//        }
//
//    }
//
//    /**
//     * get instance of EaseNotifier
//     *
//     * @return
//     */
//    public EaseNotifier getNotifier() {
//        return easeUI.getNotifier();
//    }
//
//    /**
//     * Global listener
//     * If this event already handled by an activity, you don't need handle it again
//     * activityList.size() <= 0 means all activities already in background or not in Activity Stack
//     */
//    protected void registerMessageListener() {
//        messageListener = new EMMessageListener() {
//            private BroadcastReceiver broadCastReceiver = null;
//
//            @Override
//            public void onMessageReceived(List<EMMessage> messages) {
//                for (EMMessage message : messages) {
//                    EMLog.d(TAG, "onMessageReceived id : " + message.getMsgId());
//                    LogUtil.show("msgL：来消息了 " + message.getFrom());
//                    LogUtil.show("msgL：来消息了 " + message.getBody().toString());
//
//                    // in background, do not refresh UI, notify it in notification bar
//                    EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_USER_MSG));
//                    EventBus.getDefault().post(new ChatEvent(ChatEvent.TYPE_UPDATE_ROOM_INFO));
////                    if (!easeUI.hasForegroundActivies()) {
////                        getNotifier().onNewMsg(message);
////                    }
//                    if (!GrassApp.getInstance().isSilent) {
//                        getNotifier().onNewMsg(message);
//                    }
//                }
//            }
//
//            @Override
//            public void onCmdMessageReceived(List<EMMessage> messages) {
//                for (EMMessage message : messages) {
//                    EMLog.d(TAG, "receive command message");
//                    //get message body
//                    EMCmdMessageBody cmdMsgBody = (EMCmdMessageBody) message.getBody();
//                    final String action = cmdMsgBody.action();//获取自定义action
//                    //red packet code : 处理红包回执透传消息
//                    if (!easeUI.hasForegroundActivies()) {
//
//                    }
//                    EMLog.d(TAG, String.format("Command：action:%s,message:%s", action, message.toString()));
//                }
//            }
//
//            @Override
//            public void onMessageReadAckReceived(List<EMMessage> messages) {
//            }
//
//            @Override
//            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
//            }
//
//            @Override
//            public void onMessageChanged(EMMessage message, Object change) {
//
//            }
//        };
//
//        EMClient.getInstance().chatManager().addMessageListener(messageListener);
//    }
//
//    public void initMyInfo() {
//        if (GrassApp.getInstance().isLogin()) {
//            UserInfo u = GrassApp.getInstance().getUserInfo();
//            ChatPresenter p = new ChatPresenter(null);
//            p.getContactByUid(u.account);
//        }
//    }
//
//    /**
//     * group change listener
//     */
//    class MyGroupChangeListener implements EMGroupChangeListener {
//
//        @Override
//        public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
//
////            new InviteMessgeDao(appContext).deleteMessage(groupId);
//
//            // user invite you to join group
//            InviteMessage msg = new InviteMessage();
//            msg.setFrom(groupId);
//            msg.setTime(System.currentTimeMillis());
//            msg.setGroupId(groupId);
//            msg.setGroupName(groupName);
//            msg.setReason(reason);
//            msg.setGroupInviter(inviter);
//            Log.d(TAG, "receive invitation to join the group：" + groupName);
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onInvitationDeclined(String groupId, String invitee, String reason) {
////            new InviteMessgeDao(appContext).deleteMessage(groupId);
//            //user declined your invitation
//            boolean hasGroup = false;
//            EMGroup group = null;
//            for (EMGroup _group : EMClient.getInstance().groupManager().getAllGroups()) {
//                if (_group.getGroupId().equals(groupId)) {
//                    group = _group;
//                    hasGroup = true;
//                    break;
//                }
//            }
//            if (!hasGroup)
//                return;
//
//            InviteMessage msg = new InviteMessage();
//            msg.setFrom(groupId);
//            msg.setTime(System.currentTimeMillis());
//            msg.setGroupId(groupId);
//            msg.setGroupName(group == null ? groupId : group.getGroupName());
//            msg.setReason(reason);
//            msg.setGroupInviter(invitee);
//            Log.d(TAG, invitee + "Declined to join the group：" + group == null ? groupId : group.getGroupName());
////            msg.setStatus(InviteMesageStatus.GROUPINVITATION_DECLINED);
////            notifyNewInviteMessage(msg);
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onUserRemoved(String groupId, String groupName) {
//            //user is removed from group
//            try {
//                // delete conversation
//                EMClient.getInstance().chatManager().deleteConversation(groupId, true);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtil.show(" onUserRemoved error:" + e.getMessage());
//            }
//            EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_USER_MSG));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CHAT_LIST));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onGroupDestroyed(String s, String s1) {
//            try {
//                // delete conversation
//                EMClient.getInstance().chatManager().deleteConversation(s, true);
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtil.show(" onUserRemoved error:" + e.getMessage());
//            }
//            EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_USER_MSG));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CHAT_LIST));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        //        @Override
////        public void onGrou(String groupId, String groupName) {
////            // group is dismissed,
////            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
////        }
//
//        @Override
//        public void onApplicationReceived(String groupId, String groupName, String applyer, String reason) {
//
//            // user apply to join group
//            InviteMessage msg = new InviteMessage();
//            msg.setFrom(applyer);
//            msg.setTime(System.currentTimeMillis());
//            msg.setGroupId(groupId);
//            msg.setGroupName(groupName);
//            msg.setReason(reason);
//            Log.d(TAG, applyer + " Apply to join group：" + groupName);
////            msg.setStatus(InviteMesageStatus.BEAPPLYED);
////            notifyNewInviteMessage(msg);
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onApplicationAccept(String groupId, String groupName, String accepter) {
//
//            String st4 = appContext.getString(R.string.Agreed_to_your_group_chat_application);
//            // your application was accepted
//            EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
//            msg.setChatType(EMMessage.ChatType.GroupChat);
//            msg.setFrom(accepter);
//            msg.setTo(groupId);
//            msg.setMsgId(UUID.randomUUID().toString());
//            msg.addBody(new EMTextMessageBody(accepter + " " + st4));
//            msg.setStatus(EMMessage.Status.SUCCESS);
//            // save accept message
//            EMClient.getInstance().chatManager().saveMessage(msg);
//            // notify the accept message
//            getNotifier().vibrateAndPlayTone(msg);
//
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onApplicationDeclined(String groupId, String groupName, String decliner, String reason) {
//            // your application was declined, we do nothing here in demo
//        }
//
//        @Override
//        public void onInvitationAccepted(String groupId, String invitee, String reason) {
//            boolean hasGroup = false;
//            EMGroup _group = null;
//            for (EMGroup group : EMClient.getInstance().groupManager().getAllGroups()) {
//                if (group.getGroupId().equals(groupId)) {
//                    hasGroup = true;
//                    _group = group;
//                    break;
//                }
//            }
//            if (!hasGroup)
//                return;
//
//            InviteMessage msg = new InviteMessage();
//            msg.setFrom(groupId);
//            msg.setTime(System.currentTimeMillis());
//            msg.setGroupId(groupId);
//            msg.setGroupName(_group == null ? groupId : _group.getGroupName());
//            msg.setReason(reason);
//            msg.setGroupInviter(invitee);
//            Log.d(TAG, invitee + "Accept to join the group：" + _group == null ? groupId : _group.getGroupName());
////            msg.setStatus(InviteMesageStatus.GROUPINVITATION_ACCEPTED);
////            notifyNewInviteMessage(msg);
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//
//        @Override
//        public void onAutoAcceptInvitationFromGroup(String groupId, String inviter, String inviteMessage) {
//            // got an invitation
//            String st3 = appContext.getString(R.string.Invite_you_to_join_a_group_chat);
//            EMMessage msg = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
//            msg.setChatType(EMMessage.ChatType.GroupChat);
//            msg.setFrom(inviter);
//            msg.setTo(groupId);
//            msg.setMsgId(UUID.randomUUID().toString());
//            EaseUser inviteUser = ChatUserDb.getUser(inviter);
//            if (inviteUser != null) {
//                inviter = inviteUser.nick;
//            }
//            if (inviter.equals(EMClient.getInstance().getCurrentUser())) {
//                return;
//            }
//            msg.addBody(new EMTextMessageBody(inviter + " " + st3));
//            msg.setStatus(EMMessage.Status.SUCCESS);
//            // save invitation as messages
//            EMClient.getInstance().chatManager().saveMessage(msg);
//            // notify invitation message
//
//            if (!easeUI.hasForegroundActivies()) {
//                getNotifier().onNewMsg(msg);
//            }
//            EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_USER_MSG));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CHAT_LIST));
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
////            getNotifier().vibrateAndPlayTone(msg);
//            EMLog.d(TAG, "onAutoAcceptInvitationFromGroup groupId:" + groupId);
////            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_GROUP_CHANAGED));
//        }
//    }
//
//    /***
//     * 好友变化listener
//     */
//    public class MyContactListener implements EMContactListener {
//
//        @Override
//        public void onContactAdded(String username) {
//            // save contact
//            Map<String, EaseUser> localUsers = ChatUserDb.getUserMap();
//            EaseUser user = new EaseUser(username);
//            LogUtil.show("add");
//            if (!localUsers.containsKey(username)) {
//                ChatUserDb.saveChatUser(user);
//            }
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_CONTACT_CHANAGED));
//            LogUtil.show("add");
//        }
//
//        @Override
//        public void onContactDeleted(String username) {
//            ChatUserDb.delUserInfo(username);
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_CONTACT_CHANAGED));
//
//        }
//
//        @Override
//        public void onContactInvited(String username, String reason) {
//            PrefManager.setPrefInt(GlobalApp.PRE_PUSH_NEW_FRIEND, 1);
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_NEW_FRIEND));
////            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_CONTACT_CHANAGED));
//        }
//
//        @Override
//        public void onContactAgreed(String username) {
//
////            List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();
////            for (InviteMessage inviteMessage : msgs) {
////                if (inviteMessage.getFrom().equals(username)) {
////                    return;
////                }
////            }
//            LogUtil.show("agree" + username);
//////            // save invitation as message
//            InviteMessage msg = new InviteMessage();
//            msg.setFrom(username);
//            msg.setTime(System.currentTimeMillis());
////            Log.d(TAG, username + "accept your request");
//            msg.setStatus(InviteMessage.InviteMesageStatus.BEAGREED);
//            notifyNewInviteMessage(msg);
//            EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_UPDATE_CONTACT_LIST));
//            LogUtil.show("agree");
//            broadcastManager.sendBroadcast(new Intent(GrassConstant.ACTION_CONTACT_CHANAGED));
//        }
//
//        @Override
//        public void onContactRefused(String username) {
//            // your request was refused
//            Log.d(username, username + " refused to your request");
//        }
//    }
//
//    /**
//     * save and notify invitation message
//     *
//     * @param msg
//     */
//    private void notifyNewInviteMessage(InviteMessage msg) {
//        getNotifier().vibrateAndPlayTone(null);
//    }
//
//    /**
//     * 清空群聊天记录
//     */
//    private void clearGroupHistory(String gid) {
//
//        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(gid, EMConversation.EMConversationType.GroupChat);
//        if (conversation != null) {
//            conversation.clearAllMessages();
//        }
//    }
//
//    /**
//     * 退出群组
//     *
//     * @param groupId
//     */
//    private void exitGroup(final Activity c, final String groupId) {
//        ;
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    EMClient.getInstance().groupManager().leaveGroup(groupId);
//                    c.runOnUiThread(new Runnable() {
//                        public void run() {
//                            c.setResult(Activity.RESULT_OK);
//                            c.finish();
//                            if (ChatActivity.activityInstance != null)
//                                ChatActivity.activityInstance.finish();
//                        }
//                    });
//                } catch (final Exception e) {
//                    LogUtil.show("退出失败" + e.getMessage());
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 解散群组
//     *
//     * @param groupId
//     */
//    private void deleteGroup(final Activity c, final String groupId) {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    EMClient.getInstance().groupManager().destroyGroup(groupId);
//                    c.runOnUiThread(new Runnable() {
//                        public void run() {
//                            c.setResult(Activity.RESULT_OK);
//                            c.finish();
//                            if (ChatActivity.activityInstance != null)
//                                ChatActivity.activityInstance.finish();
//                        }
//                    });
//                } catch (final Exception e) {
//                    LogUtil.show("解散失败" + e.getMessage());
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 增加群成员
//     *
//     * @param newmembers
//     */
//    private void addMembersToGroup(final String groupId, final String[] newmembers) {
//        new Thread(new Runnable() {
//
//            public void run() {
//                try {
//                    // 创建者调用add方法
//                    if (EMClient.getInstance().getCurrentUser().equals(groupId)) {
//                        EMClient.getInstance().groupManager().addUsersToGroup(groupId, newmembers);
//                    } else {
//                        // 一般成员调用invite方法
//                        EMClient.getInstance().groupManager().inviteUser(groupId, newmembers, null);
//                    }
//
//                } catch (final Exception e) {
//
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * 添加好友
//     *
//     * @param toAddUsername
//     * @param reason
//     */
//
//    public void addFriend(String toAddUsername, String reason) {
//        try {
//            EMClient.getInstance().contactManager().addContact(toAddUsername, reason);
//        } catch (Exception e) {
//
//        }
//    }
//
//
//    public void delFriend(String toAddUsername) {
//        try {
//            EMClient.getInstance().contactManager().deleteContact(toAddUsername);
//        } catch (Exception e) {
//
//        }
//    }
//
//    public void createChatRoom(String roomId) {
//        EMClient.getInstance().chatroomManager().joinChatRoom(roomId, new EMValueCallBack<EMChatRoom>() {
//
//            @Override
//            public void onSuccess(EMChatRoom value) {
//                //加入聊天室成功
//            }
//
//            @Override
//            public void onError(final int error, String errorMsg) {
//                //加入聊天室失败
//            }
//        });
//    }
//
//}
