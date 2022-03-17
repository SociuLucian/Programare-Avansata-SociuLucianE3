import java.sql.Array;

public interface Storage {
    int getStorageCapacity() ;
      /*default Array changeUnit(String unit, Computer x) {
          if (unit == "Megabyte") {
              x.Storagecap *= 1024;
              x.unit = "Mb";
          } else if (unit == "Kilobyte") {
              x.Storagecap *= 1024;
              x.Storagecap *= 1024;
              x.unit = "Kb";
          } else if (unit == "Byte") {
              x.Storagecap *= 1024;
              x.Storagecap *= 1024;
              x.Storagecap *= 1024;
              x.unit = "B";
          }

      }
*/
}
