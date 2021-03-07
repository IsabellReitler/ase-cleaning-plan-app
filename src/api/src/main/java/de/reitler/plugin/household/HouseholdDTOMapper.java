package de.reitler.plugin.household;

import de.reitler.application.household.HouseholdDTO;
import de.reitler.application.household.HouseholdInputMapper;

public class HouseholdDTOMapper implements HouseholdInputMapper {
    @Override
    public HouseholdDTO getHouseholdDTO(String id, String name) {
        return new HouseholdDTOImpl(id, name);
    }
}
