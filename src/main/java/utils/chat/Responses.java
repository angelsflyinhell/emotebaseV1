package utils.chat;

import utils.Config;

public class Responses {

    public static class Errors {
        public static String INSUFFICIENT_ARGS(String command) {
            return "Insufficient Arguments!\nUse `" + Config.PREFIX + "help " + command + "` for more information!";
        }

        public static String INSUFFICIENT_PERMS(String command) {
            return "Insufficient Permissions!\nUse `" + Config.PREFIX + "help " + command + "` for more information!";
        }
    }

    public static class Emotes {
        public static String UPLOAD_SUCCESSFUL(String emoteName) {
            return "Your Emote `:" + emoteName + ":` has been uploaded successfully!";
        }

        public static String ADD_SUCCESSFUL(String emoteName) {
            return "Your Emote `:" + emoteName + ":` has been added to this Server successfully!";
        }
    }

}
