package edu.utcn.gpstrack.server.position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Radu Miron
 * @version 1
 */
@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping("/create")
    public PositionDTO create(@RequestBody PositionDTO position) {
        return positionService.create(position);
    }

    @DeleteMapping("/delete/{id}")
    public PositionDTO delete(@PathVariable Integer id) {
        return positionService.delete(id);
    }

    @GetMapping("/get/{id}")
    public PositionDTO getPosition(@PathVariable Integer id) {
        return positionService.getPosition(id);
    }

    @PutMapping("/update/{id}")
    public PositionDTO updatePosition(@PathVariable Integer id, @RequestBody PositionDTO newPosition) {
        return positionService.updatePosition(id, newPosition);
    }

    @GetMapping("/getPositions/{terminalId}")
    public List<PositionDTO> getTerminalPositionInTime(@PathVariable String terminalId,
                                                       @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return positionService.getTerminalPositionInTime(terminalId, startDate, endDate);
    }
}
