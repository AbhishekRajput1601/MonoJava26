package test;

import model.modelservices.InventoryService;
import model.modelservices.ProductService;
import model.modelservices.ReorderService;
import model.notificationmodel.EmailNotifier;
import model.notificationmodel.SMSNotifier;
import model.valuationmodel.FIFOValuation;
import model.valuationmodel.LIFOValuation;

import java.util.List;
import java.util.Map;

public class InventorySystemMain {

    public static void main(String[] args) {

        InventoryService service = new InventoryService(
                List.of(new EmailNotifier(), new SMSNotifier()),
                new ReorderService(),
                new ProductService(),
                Map.of(true, new FIFOValuation(), false, new LIFOValuation())
        );

        new InventoryMenu(service).start();
    }
}
