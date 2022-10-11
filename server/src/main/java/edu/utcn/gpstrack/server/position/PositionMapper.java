package edu.utcn.gpstrack.server.position;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionMapper {
    public Position convert(PositionDTO positionDTO) {

        Position position = new Position();
        position.setLatitude(positionDTO.getLatitude());
        position.setLongitude(positionDTO.getLongitude());
        position.setTerminalId(positionDTO.getTerminalId());

        return position;
    }

    public PositionDTO convert(Position position) {

        PositionDTO positionDTO = new PositionDTO();
        positionDTO.setId(position.getId());
        positionDTO.setLatitude(position.getLatitude());
        positionDTO.setLongitude(position.getLongitude());
        positionDTO.setTerminalId(position.getTerminalId());
        positionDTO.setCreationDate(position.getCreationDate());

        return positionDTO;
    }

    public List<PositionDTO> convert(Collection<Position> positions) {
        return positions.stream().map(position -> {
            return convert(position);
        }).collect(Collectors.toList());
    }

}
