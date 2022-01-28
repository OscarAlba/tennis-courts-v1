package com.tenniscourts.guests;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private GuestMapper guestMapper;

    private GuestRepository guestRepository;

    public GuestDTO addGuest(CreateGuestRequestDTO createGuestRequestDTO) {

        return guestMapper.map(guestRepository.saveAndFlush(guestMapper.map(createGuestRequestDTO)));
    }

    public GuestDTO findByName(String name){

        Guest dto = guestRepository.findByName(name);

        if(dto == null){
            throw new IllegalArgumentException("Guest not found");
        }

        return guestMapper.map(dto);
    }

    public GuestDTO findGuest(Long guestId) {

        Optional<Guest> guest = guestRepository.findById(guestId);

        if(!guest.isPresent()){
            throw new IllegalArgumentException("Guest not found.");
        }

        return guestMapper.map(guest.get());
    }

    public List<GuestDTO> findAllGuests(){

        return guestMapper.map(guestRepository.findAll());
    }

    public GuestDTO updateGuest(CreateGuestRequestDTO createGuestRequestDTO,Long guestId){

        Optional<Guest> opGuest = guestRepository.findById(guestId);

        if(!opGuest.isPresent()){
            throw new IllegalArgumentException("Guest not found.");
        }
        Guest guest = opGuest.get();
        guest.setName(createGuestRequestDTO.getName());

        return guestMapper.map(guestRepository.saveAndFlush(guest));

    }


    public void deleteGuest(Long guestId) {

        Optional<Guest> opGuest = guestRepository.findById(guestId);

        if(!opGuest.isPresent()){
            throw new IllegalArgumentException("Guest not found.");
        }

        guestRepository.delete(opGuest.get());
        guestRepository.flush();


    }

}
