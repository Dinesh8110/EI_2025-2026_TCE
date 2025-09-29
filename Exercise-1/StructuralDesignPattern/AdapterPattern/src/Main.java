import java.util.*;

// Target interface (USB-C)
interface USB_C {
    void connectTypeC();
}

// Adaptee (old USB-A device)
class OldDevice {
    void connectTypeA() { System.out.println("Connected using USB-A"); }
}

// Adapter to make OldDevice compatible with USB-C
class Adapter implements USB_C {
    private OldDevice device;
    Adapter(OldDevice device) { this.device = device; }
    public void connectTypeC() { device.connectTypeA(); }
}

public class Main {
    public static void main(String[] args) {
        OldDevice old = new OldDevice();      // Old USB-A device
        USB_C adapter = new Adapter(old);     // Adapter bridges to USB-C
        adapter.connectTypeC();               // Connect using USB-C
    }
}
