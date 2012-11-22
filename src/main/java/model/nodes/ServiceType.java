package model.nodes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ServiceType{
	COMPLETE_VEHICLE,TRUCK;
	private static final List<ServiceType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	
	public static ServiceType randomServiceType(Random random)  {
		return VALUES.get(random.nextInt(SIZE));
	}
}