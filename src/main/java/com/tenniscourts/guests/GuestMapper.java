package com.tenniscourts.guests;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    Guest map(CreateGuestRequestDTO source);

    GuestDTO map(Guest source);

    Guest map(GuestDTO source);

    List<GuestDTO> map(List<Guest> source);
}

