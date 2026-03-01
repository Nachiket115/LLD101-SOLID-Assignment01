import java.util.*;

public class DeviceRegistry {
    private final java.util.List<SmartClassroomDevice> devices = new ArrayList<>();

    public void add(SmartClassroomDevice d) { devices.add(d); }

    public <T extends SmartClassroomDevice> T getFirstByCapability(Class<T> capability) {
        for (SmartClassroomDevice d : devices) {
            if (capability.isInstance(d)) return capability.cast(d);
        }
        throw new IllegalStateException("Missing: " + capability.getSimpleName());
    }
}
