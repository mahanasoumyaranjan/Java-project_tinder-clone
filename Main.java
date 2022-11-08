package DataTable;
import DataTable.Datasources;
public class Main {
    public static void main(String[] args) {
        Datasources datasource = new Datasources();
        if(!datasource.open()){
            System.out.println("Can't open the database");
            return;
        }
        datasource.close();
    }
}
