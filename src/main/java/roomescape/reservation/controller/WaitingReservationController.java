package roomescape.reservation.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.auth.domain.AuthInfo;
import roomescape.global.annotation.LoginUser;
import roomescape.reservation.controller.dto.ReservationRequest;
import roomescape.reservation.controller.dto.ReservationResponse;
import roomescape.reservation.service.ReservationService;

import java.net.URI;

@RestController
@RequestMapping("/reservations/waiting")
public class WaitingReservationController {

    private final ReservationService reservationService;

    public WaitingReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@LoginUser AuthInfo authInfo,
                                                      @RequestBody @Valid ReservationRequest reservationRequest) {
        ReservationResponse response = reservationService.createMemberReservation(authInfo, reservationRequest);
        return ResponseEntity.created(URI.create("/reservations/" + response.memberReservationId())).body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@LoginUser AuthInfo authInfo,
                       @PathVariable("id") @Min(1) long reservationMemberId) {
        reservationService.deleteMemberReservation(authInfo, reservationMemberId);
    }
}
