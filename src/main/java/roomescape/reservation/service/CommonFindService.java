package roomescape.reservation.service;

import org.springframework.stereotype.Service;
import roomescape.exception.custom.BadRequestException;
import roomescape.member.domain.Member;
import roomescape.member.domain.repository.MemberRepository;
import roomescape.reservation.domain.MemberReservation;
import roomescape.reservation.domain.ReservationSlot;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.domain.Theme;
import roomescape.reservation.domain.repository.MemberReservationRepository;
import roomescape.reservation.domain.repository.ReservationSlotRepository;
import roomescape.reservation.domain.repository.ReservationTimeRepository;
import roomescape.reservation.domain.repository.ThemeRepository;

import java.time.LocalDate;

@Service
public class CommonFindService {

    private final ReservationTimeRepository reservationTimeRepository;
    private final ThemeRepository themeRepository;
    private final MemberRepository memberRepository;
    private final ReservationSlotRepository reservationSlotRepository;
    private final MemberReservationRepository memberReservationRepository;

    public CommonFindService(ReservationTimeRepository reservationTimeRepository,
                             ThemeRepository themeRepository,
                             MemberRepository memberRepository,
                             ReservationSlotRepository reservationSlotRepository,
                             MemberReservationRepository memberReservationRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.themeRepository = themeRepository;
        this.memberRepository = memberRepository;
        this.reservationSlotRepository = reservationSlotRepository;
        this.memberReservationRepository = memberReservationRepository;
    }

    public ReservationTime getReservationSlotTime(long timeId) {
        return reservationTimeRepository.findById(timeId)
                .orElseThrow(() -> new BadRequestException("해당 ID에 대응되는 예약 시간이 없습니다."));
    }

    public Theme getTheme(long themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new BadRequestException("해당 ID에 대응되는 테마가 없습니다."));
    }

    public Member getMember(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BadRequestException("해당 유저를 찾을 수 없습니다."));
    }

    public ReservationSlot getReservationSlot(LocalDate date, ReservationTime time, Theme theme) {
        return reservationSlotRepository.findByDateAndTimeAndTheme(date, time, theme)
                .orElseGet(() -> reservationSlotRepository.save(new ReservationSlot(date, time, theme)));
    }

    public MemberReservation getMemberReservation(long memberReservationId) {
        return memberReservationRepository.findById(memberReservationId)
                .orElseThrow(() -> new BadRequestException("해당 ID에 대응되는 사용자 예약이 없습니다."));
    }
}
