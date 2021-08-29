public enum EnuConect {
        USER ("alex"),
        PASSWORD ("alex"),
        NAME_ADRESS ("user_address"),
        NAME_USERS ("users");



        private String str;

        EnuConect(String str) {
            this.str = str;
        }
        public String getEnuConect() {
                return str;
        }
}
