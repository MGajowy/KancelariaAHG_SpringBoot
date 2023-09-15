package pl.kancelaria.AHG.externalApi.saosApi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SaosServiceTest {

    @InjectMocks
    SaosService saosService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(saosService, "saosHost", "https://www.saos.org.pl/api/search/judgments?pageSize=10&courtType=COMMON&sortingField=JUDGMENT_DATE&sortingDirection=DESC");
    }

    @Test
    void shouldGetJudgments() throws IOException, URISyntaxException, InterruptedException {
        //given
        //when
        HttpResponse<String> actual = saosService.getTenJudgments();
        //then
        assertThat(actual.statusCode()).isEqualTo(200);

    }

}