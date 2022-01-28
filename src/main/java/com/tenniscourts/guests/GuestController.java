package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/guest")
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @PostMapping(value = "/add")
    public ResponseEntity<GuestDTO> addGuest(@RequestBody CreateGuestRequestDTO createGuestRequestDTO) {

        GuestDTO dto = guestService.addGuest(createGuestRequestDTO);
        ResponseEntity.created(locationByEntity(dto.getId())).build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{guestId}")
    public ResponseEntity<GuestDTO> findGuest(@PathVariable Long guestId) {
        return ResponseEntity.ok(guestService.findGuest(guestId));
    }

    @GetMapping(value = "/")
    public ResponseEntity<GuestDTO> findGuestByName(@RequestBody CreateGuestRequestDTO
                                                                createGuestRequestDTO) {
        return ResponseEntity.ok(guestService.findByName(createGuestRequestDTO.getName()));
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<GuestDTO>> findAllGuest() {
        return ResponseEntity.ok(guestService.findAllGuests());
    }

    @PutMapping(value = "/update/{guestId}")
    public ResponseEntity<GuestDTO> updateGuest(@ApiParam("Guest request payload")
                                            @RequestBody CreateGuestRequestDTO createGuestRequestDTO,
                                            @ApiParam("Guest Id") @PathVariable Long guestId){

        GuestDTO dto = guestService.updateGuest(createGuestRequestDTO, guestId);
        ResponseEntity.created(locationByEntity(dto.getId())).build();

        return ResponseEntity.ok(dto);
    }
    @DeleteMapping(value = "/delete/{guestId}")
    public ResponseEntity<Void> deleteGuest( @ApiParam("Guest Id") @PathVariable Long guestId) {

        guestService.deleteGuest(guestId);
        return ResponseEntity.ok().build();
    }
}
