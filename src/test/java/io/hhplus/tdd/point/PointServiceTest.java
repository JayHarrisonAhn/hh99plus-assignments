package io.hhplus.tdd.point;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointServiceTest {

    private AutoCloseable closeable;

    @Mock
    PointManager pointManager;

    @InjectMocks
    PointService pointService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
        this.userId = 1;
        this.userPoint = new UserPoint(userId, 2, 3);
        this.userPointHistory = new ArrayList<>();
        this.userPointHistory.add(new PointHistory(1, userId, 3, TransactionType.CHARGE, 3));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    long userId;
    UserPoint userPoint;
    List<PointHistory> userPointHistory;

    @Test
    void viewPointAmount_success() {
        given(
                pointManager.getPoint(this.userId)
        ).willReturn(
                this.userPoint
        );

        assertEquals(this.userPoint, pointService.viewPointAmount(this.userPoint.id()));
    }

    @Test
    void viewPointHistory_success() {
        given(
                pointManager.getPointHistory(this.userId)
        ).willReturn(
                this.userPointHistory
        );

        assertEquals(this.userPointHistory, pointService.viewPointHistory(this.userId));
    }

    @Test
    void chargePoint_fail_minusAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> pointService.chargePoint(this.userId, -1000)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> pointService.chargePoint(this.userId, 0)
        );
    }

    @Test
    void usePoint_fail_minusAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> pointService.usePoint(this.userId, -1000)
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> pointService.usePoint(this.userId, 0)
        );
    }
}