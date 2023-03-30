import com.grivera.generator.sensors.DataNode;
import com.grivera.generator.sensors.SensorNode;
import com.grivera.generator.sensors.StorageNode;

public class PlaygroundTester {
    public static void main(String[] args) {
        SensorNode.setBitsPerPacket(512 * 8);

        DataNode start = new DataNode(0, 0, 30, 1, 1800);
        StorageNode end = new StorageNode(30, 0, 30, 1);

        System.out.println(start);
        System.out.println(end);
        System.out.println();

        int totalCost = start.calculateTransmissionCost(end) + end.calculateReceivingCost();
        System.out.printf("The distance between %s and %s is %f\n", start.getName(), end.getName(), start.distanceTo(end));
        System.out.printf("The cost (at %d bits/packet) is %d micro J (10^-6 J)\n",
                SensorNode.getBitsPerPacket() , totalCost);
        System.out.printf("E_T = %d micro J; E_R = %d micro J\n", start.calculateTransmissionCost(end), end.calculateReceivingCost());
        System.out.println();

        System.out.printf("DN's packet is valued at %d\n", start.getOverflowPacketValue());
        System.out.printf("The profit of sending 1 packet from %s to %s is %d\n",
                start.getName(), end.getName(), start.getOverflowPacketValue() - totalCost);

    }
}
