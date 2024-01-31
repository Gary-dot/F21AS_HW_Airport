package Algorithm;

import DataStructure.Baggage;
import DataStructure.Flight;

/**
 * The penalty rule is used to calculate the penalty for a passenger
 * whose baggage exceeds the baggage limit.
 * Specifically, the penalty is calculated as follows:
 * 1. If the baggage exceeds the volume limit,
 * every inch of the baggage that exceeds the volume limit is charged £10.
 * For example, if the baggage limit is 24x16x10, and the baggage is 30x15x15,
 * then the penalty is calculated as follows:
 * 30 - 24 = 6, 15-10 = 5. So the penalty is (5 + 6) * 10 = £110.
 * <p>
 * 2. If the baggage exceeds the weight limit,
 * every pound of the baggage that exceeds the weight limit is charged £25.
 * For example, if the weight limit is 50 pounds, and the baggage is 60 pounds,
 * then the penalty is (60 - 50) * 25 = £250.
 */
public class PenaltyRule {
    /**
     * Calculate the penalty for a passenger whose baggage exceeds the baggage limit.
     *
     * @param flight  The flight.
     * @param baggage The baggage of the passenger.
     * @return The penalty, including the excess size penalty, the excess weight penalty and the total penalty.
     */
    public static int[] calculateExcessPenalty(Flight flight, Baggage baggage) {
        int[] excessFee = new int[3];
        Baggage baggageLimit = flight.getBaggageLimit();
        int totalSizeExcess = 0;
        if (baggage.getLength() > baggageLimit.getLength()) {
            totalSizeExcess += baggage.getLength() - baggageLimit.getLength();
        }
        if (baggage.getWidth() > baggageLimit.getWidth()) {
            totalSizeExcess += baggage.getWidth() - baggageLimit.getWidth();
        }
        if (baggage.getHeight() > baggageLimit.getHeight()) {
            totalSizeExcess += baggage.getHeight() - baggageLimit.getHeight();
        }

        int totalWeightExcess = 0;
        if(baggage.getWeight() > baggageLimit.getWeight()) {
            totalWeightExcess = baggage.getWeight() - baggageLimit.getWeight();
        }

        excessFee[0] = totalSizeExcess * 10;
        excessFee[1] = totalWeightExcess * 25;
        excessFee[2] = excessFee[0] + excessFee[1];
        return excessFee;
    }
}
