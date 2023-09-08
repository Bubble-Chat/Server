package kr.hs.dgsw.bubblechat.apiServer.service.impl;

import kr.hs.dgsw.bubblechat.apiServer.domain.User;
import kr.hs.dgsw.bubblechat.apiServer.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service(value = "providerService")
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService {

    @Override
    public User getUserByToken(String provider, String token) {
        if ("google".equals(provider)) {
            return getUserByGoogle(token);
        } else if ("naver".equals(provider)) {
            return getUserByNaver(token);
        } else if ("kakao".equals(provider)) {
            return getUserByKaKao(token);
        } else {
            throw new RuntimeException("unknown provider");
        }


    }

    private User getUserByGoogle(String token) {

        try {
            URL url = new URL("https://www.googleapis.com/oauth2/v2/userinfo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            conn.setRequestProperty("Authorization", "Bearer " + token);

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            log.info("[response] : " + content.toString());

            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(content.toString());
            JSONObject jsonObj = (JSONObject) obj;

            String email = (String) jsonObj.get("email");
            String name = (String) jsonObj.get("name");

            return User.builder().email(email).name(name).build();

        } catch(MalformedURLException e) { } catch (IOException e) { } catch (ParseException e) { }

        return null;
    }

    private User getUserByNaver(String token) {
        // TODO

        return null;
    }

    private User getUserByKaKao(String token) {
        // TODO

        return null;
    }
}
