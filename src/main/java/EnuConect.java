public enum EnuConect {
        USER ("alex"),
        PASSWORD ("alex"),
        NAME ("users");


        private String str;

        EnuConect(String str) {
            this.str = str;
        }
        public String getEnuConect() {
                return str;
        }
}
