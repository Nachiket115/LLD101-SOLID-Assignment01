public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        ProjectorControl pj = reg.getFirstByCapability(ProjectorControl.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        LightingControl lights = reg.getFirstByCapability(LightingControl.class);
        lights.setBrightness(60);

        ClimateControl ac = reg.getFirstByCapability(ClimateControl.class);
        ac.setTemperatureC(24);

        AttendanceScannable scan = reg.getFirstByCapability(AttendanceScannable.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstByCapability(ProjectorControl.class).powerOff();
        reg.getFirstByCapability(LightingControl.class).powerOff();
        reg.getFirstByCapability(ClimateControl.class).powerOff();
    }
}
