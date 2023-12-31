public class HashEntry {
      private Long key;
      private String value;

      HashEntry(Long key, String value) {
            this.key = key;
            this.value = value;
      }     

      public Long getKey() {
            return key;
      }

      public String  getValue() {
            return value;
      }
      
      public void setValue(String val) {
            this.value = val;
      }
}
