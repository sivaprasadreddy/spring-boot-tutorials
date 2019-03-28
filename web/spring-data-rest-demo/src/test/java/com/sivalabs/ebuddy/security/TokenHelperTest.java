package com.sivalabs.ebuddy.security;

import com.sivalabs.ebuddy.config.AppConfiguration;
import com.sivalabs.ebuddy.config.TimeProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class TokenHelperTest {

    private static final String TEST_USERNAME = "testUser";

    @InjectMocks
    private TokenHelper tokenHelper;

    @Mock
    private TimeProvider timeProviderMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        AppConfiguration appConfiguration = new AppConfiguration();
        appConfiguration.getJwt().setExpiresIn(10L);
        appConfiguration.getJwt().setSecret("mySecret");
        ReflectionTestUtils.setField(tokenHelper, "appConfiguration", appConfiguration);
    }

    @Test
    public void testGenerateTokenGeneratesDifferentTokensForDifferentCreationDates() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now());

        final String token = createToken();
        final String laterToken = createToken();

        assertThat(token).isNotEqualTo(laterToken);
    }

    @Test
    public void getUsernameFromToken() {
        when(timeProviderMock.now()).thenReturn(DateUtil.now());

        final String token = createToken();

        assertThat(tokenHelper.getUsernameFromToken(token)).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void getCreatedDateFromToken() {
        final Date now = DateUtil.now();
        when(timeProviderMock.now()).thenReturn(now);

        final String token = createToken();

        assertThat(tokenHelper.getIssuedAtDateFromToken(token)).isInSameMinuteWindowAs(now);
    }

    @Test(expected = ExpiredJwtException.class)
    public void expiredTokenCannotBeRefreshed() {
        when(timeProviderMock.now()).thenReturn(DateUtil.yesterday());

        String token = createToken();
        tokenHelper.refreshToken(token);
    }

    @Test
    public void getAudienceFromToken() {
        when(timeProviderMock.now()).thenReturn(DateUtil.now());

        final String token = createToken();

        assertThat(tokenHelper.getAudienceFromToken(token)).isEqualTo(tokenHelper.AUDIENCE_WEB);
    }

    @Test
    public void canRefreshToken() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.tomorrow());
        String firstToken = createToken();
        String refreshedToken = tokenHelper.refreshToken(firstToken);
        Date firstTokenDate = tokenHelper.getIssuedAtDateFromToken(firstToken);
        Date refreshedTokenDate = tokenHelper.getIssuedAtDateFromToken(refreshedToken);
        assertThat(firstTokenDate).isBefore(refreshedTokenDate);
    }

    private String createToken() {
        return tokenHelper.generateToken(TEST_USERNAME);
    }

}
