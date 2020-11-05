package liveproject.webreport.result;

import liveproject.webreport.match.Match;
import liveproject.webreport.match.MatchRepository;
import liveproject.webreport.match.MatchService;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

	@InjectMocks
	private MatchService matchService = new MatchService();

	@Mock
	private MatchRepository matchRepositoryMock;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	@Ignore
	public void shouldInitializeWithTwoDemoUsers() {
		// act
//		resultService.initialize();
		// assert
		verify(matchRepositoryMock, times(2)).save(any(Match.class));
	}

	@Test
	@Ignore
	public void shouldThrowExceptionWhenUserNotFound() {
		// arrange
		thrown.expect(UsernameNotFoundException.class);
		thrown.expectMessage("user not found");

//		when(resultRepositoryMock.findOneByEmail("user@example.com")).thenReturn(null);
//		// act
//		resultService.loadUserByUsername("user@example.com");
	}

	@Test
	@Ignore
	public void shouldReturnUserDetails() {
		// arrange
//		Result demoUser = new Result("user@example.com", "demo", "ROLE_USER");
//		when(resultRepositoryMock.findOneByEmail("user@example.com")).thenReturn(demoUser);
//
//		// act
//		UserDetails userDetails = resultService.loadUserByUsername("user@example.com");

		// assert
//		assertThat(demoUser.getEmail()).isEqualTo(userDetails.getUsername());
//		assertThat(demoUser.getPassword()).isEqualTo(userDetails.getPassword());
//		assertThat(hasAuthority(userDetails, demoUser.getRole())).isTrue();
	}
}
