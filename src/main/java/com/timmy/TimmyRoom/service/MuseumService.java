package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.dto.Direction;
import com.timmy.TimmyRoom.dto.Location;
import com.timmy.TimmyRoom.dto.request.MuseumGetReqeustDTO;
import com.timmy.TimmyRoom.entity.Museum;
import com.timmy.TimmyRoom.repository.MuseumRepository;
import com.timmy.TimmyRoom.util.GeometryUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuseumService {

    private final EntityManager em;
    private final MuseumRepository museumRepository;

    @Transactional
    public List<Museum> getNearByMuseum(MuseumGetReqeustDTO museumGetReqeustDTO){
        Double latitude = museumGetReqeustDTO.getLatitude();
        Double longitude = museumGetReqeustDTO.getLongitude();
        Double distance = museumGetReqeustDTO.getDistance();

        Location northEast = GeometryUtil.calculate(latitude, longitude, distance, Direction.NORTHEAST.getBearing());
        Location southWest = GeometryUtil.calculate(latitude, longitude, distance, Direction.SOUTHWEST.getBearing());

        double x1 = northEast.getLatitude();
        double y1 = northEast.getLongitude();

        double x2 = southWest.getLatitude();
        double y2 = southWest.getLongitude();

        String sql = "SELECT id, name, latitude, longitude " +
                "FROM museum " +
                "WHERE ST_Contains(" +
                "ST_GeomFromText('POLYGON((" + y1 + " " + x1 + ", " + y1 + " " + x2 + ", " + y2 + " " + x2 + ", " + y2 + " " + x1 + ", " + y1 + " " + x1 + "))'), " +
                "ST_GeomFromText(CONCAT('POINT(" + longitude + " " + latitude + ")'))" +
                ")";
        Query query = em.createNativeQuery(sql);

        List<Museum> museums = query.getResultList();
        return museums;
    }

    public Museum findById(Long museumId) {
        return museumRepository.findById(museumId).orElseThrow(() -> new EntityNotFoundException());
    }
}
