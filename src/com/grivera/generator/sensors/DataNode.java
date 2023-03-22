package com.grivera.generator.sensors;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Sensor Node in a com.grivera.generator.Network that has overflow data packets to store.
 *
 * @see SensorNode
 */
public class DataNode extends SensorNode {

    private static int idCounter = 1;
    private int overflowPackets;
    private List<Integer> overflowPacketValues;
    private int packetsLeft;

    public DataNode(double x, double y, double tr, int overflowPackets, List<Integer> overflowPacketValues) {
        super(x, y, tr, String.format("DN%02d", idCounter++), overflowPackets);
        this.setOverflowPackets(overflowPackets);
        this.overflowPacketValues = new ArrayList<>(overflowPacketValues);
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

    public int getPacketsLeft() {
        return this.packetsLeft;
    }

    @Override
    public int calculateStorageCost() {
        return 0;
    }

    public int getOverflowPacketValue(int packetIndex) {
        return this.overflowPacketValues.get(packetIndex);
    }

    public static void resetCounter() {
        idCounter = 1;
    }

}
