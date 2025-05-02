package enums;

public class Enum {
    public enum Option {
        TRANSLATE(1),
        SEARCH(2),
        HISTORY(3),
        ADDSYNONYMS(4),
        EXIT(5);

        private final int code;

        Option(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        // Tìm enum theo code
        public static Option fromCode(int code) {
            for (Option opt : Option.values()) {
                if (opt.code == code) return opt;
            }
            return null;
        }
    }
}
