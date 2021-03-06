package ir.sharif.aichallenge.server.common.network.data;

import lombok.Getter;

@Getter
public abstract class UpgradeInfo extends ClientMessageInfo {
    private final int unitId;

    public UpgradeInfo(int unitId) {
        this.unitId = unitId;
    }
}
