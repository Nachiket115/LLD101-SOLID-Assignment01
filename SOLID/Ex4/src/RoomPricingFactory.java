public class RoomPricingFactory {
    public static RoomPricing create(int roomType) {
        return switch (roomType) {
            case LegacyRoomTypes.SINGLE -> new SingleRoomPricing();
            case LegacyRoomTypes.DOUBLE -> new DoubleRoomPricing();
            case LegacyRoomTypes.TRIPLE -> new TripleRoomPricing();
            default -> new DeluxeRoomPricing();
        };
    }
}

class SingleRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyPrice() {
        return new Money(14000.0);
    }
}

class DoubleRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyPrice() {
        return new Money(15000.0);
    }
}

class TripleRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyPrice() {
        return new Money(12000.0);
    }
}

class DeluxeRoomPricing implements RoomPricing {
    @Override
    public Money getMonthlyPrice() {
        return new Money(16000.0);
    }
}
