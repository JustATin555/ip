public class ForgetfulDave {
    // 3D-ASCII Art generated with https://patorjk.com/software/taag/
    private static final String DAVE_LOGO = " ________  ________  ___      ___ _______      \n"
        + "|\\   ___ \\|\\   __  \\|\\  \\    /  /|\\  ___ \\     \n"
        + "\\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\  /  / | \\   __/|    \n"
        + " \\ \\  \\ \\\\ \\ \\   __  \\ \\  \\/  / / \\ \\  \\_|/__  \n"
        + "  \\ \\  \\_\\\\ \\ \\  \\ \\  \\ \\    / /   \\ \\  \\_|\\ \\ \n"
        + "   \\ \\_______\\ \\__\\ \\__\\ \\__/ /     \\ \\_______\\\n"
        + "    \\|_______|\\|__|\\|__|\\|__|/       \\|_______|\n";

    /**
     * Print a message with a horizontal line underneath
     * @param msg message to print
     */
    private static void printWithLine(String msg) {
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    /**
     * Main entry point
     * @param args any terminal arguments for Forgetful Dave
     */
    public static void main(String[] args) {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        printWithLine("Hello! I'm Duke? Dan? Dave? Something like that...\nHow can I help?");

        // Send goodbye message
        printWithLine("See you around!");
    }
}
