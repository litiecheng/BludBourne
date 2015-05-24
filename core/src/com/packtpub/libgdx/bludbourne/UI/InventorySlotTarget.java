package com.packtpub.libgdx.bludbourne.UI;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;

public class InventorySlotTarget extends Target {

    InventorySlot _targetSlot;

    public InventorySlotTarget(InventorySlot actor){
        super(actor);
        _targetSlot = actor;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void reset(Source source, Payload payload) {
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        InventoryItem sourceActor = (InventoryItem) payload.getDragActor();
        InventoryItem targetActor = _targetSlot.getTopInventoryItem();

        if( sourceActor == null ) {
            return;
        }

        if( !_targetSlot.hasItem() ){
            _targetSlot.add(sourceActor);
        }else{
            //If the same item and stackable, add
            if( sourceActor.isSameItemType(targetActor) && sourceActor.isStackable()){
                _targetSlot.add(sourceActor);
            }else{
                //If they aren't the same items or the items aren't stackable, then swap
                InventorySlot.swapSlots(((InventorySlotSource)source)._sourceSlot, _targetSlot, sourceActor);
            }
        }

    }
}
