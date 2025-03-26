package github.alecsio.mmceaddons.common.tile.handler;

import WayofTime.bloodmagic.meteor.Meteor;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;

public interface IMeteorHandler {
    CraftCheck canHandle(Meteor meteor);
    void handle(Meteor meteor);
}
