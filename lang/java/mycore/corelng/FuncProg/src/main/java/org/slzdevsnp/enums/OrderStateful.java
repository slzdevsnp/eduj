package org.slzdevsnp.enums;

public class OrderStateful {
    private OrderState state;
    private Integer id;

    public enum OrderState{
        INITIATED,
        PAID,
        ASEMMBLED,
        DELIVERED
    }

    public OrderStateful(Integer id, OrderState state){
        this.id = id;
        this.state = state;
    }

    public OrderState getState(){
        return state;
    }

    public Boolean isDelivered(){
        if (getState() == OrderState.DELIVERED) {
            return true;
        }
        return false;
    }

    public int getDeliveryTimeInDays() {
        switch (state) {
            case INITIATED: return 5;
            case PAID: return 4;
            case ASEMMBLED: return 3;
            case DELIVERED: return 0;
        }
        return 0;
    }

}
