package edu.utcn.gpstrack.server.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Radu Miron
 * @version 1
 */
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    public PositionService(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PositionDTO create(PositionDTO positionDto) {
        Position savedPosition = positionRepository.save(positionMapper.convert(positionDto));
        return positionMapper.convert(savedPosition);
    }

    @Transactional
    public PositionDTO delete(Integer id) {
        Position positionToDelete = positionRepository.getOne(id);
        positionRepository.delete(positionToDelete);
        return positionMapper.convert(positionToDelete);
    }

    @Transactional
    public PositionDTO getPosition(Integer id) {
        Position requestedPosition = positionRepository.getOne(id);
        return positionMapper.convert(requestedPosition);
    }

    @Transactional
    public PositionDTO updatePosition(Integer id, PositionDTO newPosition) {

        return positionMapper.convert(positionRepository.findById(id).map(position -> {
            position.setTerminalId(newPosition.getTerminalId());
            position.setLatitude(newPosition.getLatitude());
            position.setLongitude(newPosition.getLongitude());
            return positionRepository.save(position);
        }).orElseGet(() -> positionRepository.save(positionMapper.convert(newPosition))));
    }

    @Transactional
    public List<PositionDTO> getTerminalPositionInTime(String terminalId, LocalDateTime startDate, LocalDateTime endDate) {

        Timestamp time1 = Timestamp.valueOf(startDate);
        Timestamp time2 = Timestamp.valueOf(endDate);
        return positionMapper.convert(positionRepository.getTerminalPositionsBetweenTwoDates(
                        terminalId, time1, time2))
                .stream()
                .filter(positionDTO ->
                        positionDTO.getCreationDate().toInstant().isBefore(endDate.toInstant(OffsetDateTime.now().getOffset()))
                                && positionDTO.getCreationDate().toInstant().isAfter(startDate.toInstant(OffsetDateTime.now().getOffset())))
                .collect(Collectors.toList());

    }
}
