package com.grivera.generator.sensors;

/**
 * Represents a Sensor Node in a com.grivera.generator.Network that has overflow data packets to store.
 *
 * @see SensorNode
 */
public class DataNode extends SensorNode {

    private static int idCounter = 1;
    private int id;
    private int overflowPackets;
    private int overflowPacketsValue;
    private int packetsLeft;

    public DataNode(double x, double y, double tr, int overflowPackets, int overflowPacketsValue) {
        super(x, y, tr, String.format("DN%02d", idCounter));
        this.id = idCounter++;
        this.setOverflowPackets(overflowPackets);
        this.overflowPacketsValue = overflowPacketsValue;
    }

    public void setOverflowPackets(int overflowPackets) {
        this.overflowPackets = overflowPackets;
        this.packetsLeft = overflowPackets;
    }

    public int getOverflowPackets() {
        return this.overflowPackets;
    }

    public boolean isEmpty() {
        return this.packetsLeft < 1;
    }

    public boolean canRemovePackets(int deltaPackets) {
        return this.packetsLeft - deltaPackets >= 0;
    }

    public void removePackets(int packets) {
        if (!this.canRemovePackets(packets)) {
            throw new IllegalArgumentException(
                    String.format("%s cannot remove %d packets (%d/%d left)",
                            this.getName(), packets, this.packetsLeft, this.overflowPackets
                    )
            );
        }
        this.packetsLeft -= packets;
    }

    @Override
    public void resetPackets() {
        this.packetsLeft = this.overflowPackets;
    }

    @Override
    public int getId() {
        return this.id;
    }

    public int getPacketsLeft() {
        return this.packetsLeft;
    }

    public int getOverflowPacketValue() {
        return this.overflowPacketsValue;
    }

    public static void resetCounter() {
        idCounter = 1;
    }

}
