package by.mmkle.proxy;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.json.simple.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="AMO-SERVICE-PROXY", url="https://fvantop.amocrm.ru")
public interface AmoServiceProxy {

    @GetMapping(value = "/api/v4/account", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getInfo(@RequestHeader("Bearer") String token);

    @GetMapping("/api/v4/contacts")
    JSONObject getAllContacts(@RequestHeader("x-access-token") String token);

    @PostMapping(value = "/api/v4/contacts", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject addContact(@RequestHeader("x-access-token") String token, @RequestBody String body);

    @PostMapping(value = "/oauth2/access_token", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject getAccessToken(@RequestBody String body);

    @PostMapping(value = "/oauth2/access_token", consumes = MediaType.APPLICATION_JSON_VALUE)
    JSONObject refreshToken(@RequestBody String body);

    public static String bodyForHaveNewAccessToken = "{\n" +
            "  \"client_id\": \"61fdb7aa-5bcc-44a8-b696-cd5a02e44b9d\",\n" +
            "  \"client_secret\": \"DUZS0HL0IAp69wUZ3MXymCFsZ7ccBp2cA2D9mIEIm4cbfOAIz8syrriJeHCKBe5q\",\n" +
            "  \"grant_type\": \"refresh_token\",\n" +
            "  \"refresh_token\": \"def50200ba92e457a95089b24ea256f9bf906a7e83edeb7597424055b065d017af7c0642d12d85d8c72d44c2809570db62c20db7f96699f2c22474a1e4b9ed286c60111623bf60e89ce6a831a4e71b2bba7b5e371b1a393da4d28acafc5c1804b9684aaabc603ac089d4a23b03be1ec205c578fa168add317cee1e4348d9269933a5ef4aaeed3e31c4f79ef6fd030922fafd91e8938dfe390c6c0e0158ea6364f5a65792bc4fbd61a08ec12397ef585c3a82b57777bcaf848dfc4c7903a1f189ebb0992b9d7b54e07c923d8e4c56cf3fc8c1a8b0d27711105830bde0569faf80bbee99ced704faef96eb00d37ee5f41809186b02d5e3cbb56fff5b7e78e00e77300fcde04cc8d14647d7464947a394fbcb7388d7b975fbc11e80b7957bef3ec72fa614090838b201337a3d51ef1dc1bc869ee69b6fc61f9b2c20e80d47271584a1f7ae1aad6cf2c231911544298503eb6940ab9d7d72a4f1b24aab11d3b4b23f516abb55b6e79feda67721be5b33af469d7adb1d837448cbfea94d8c4edadf59a7ad53d5820d2ce16646f2bd9424df21bb6071c9d0cd468318a5b0e9a0b20e14a9651c0d628d1b99e28009c41757cd79b2a1ead3\",\n" +
            "  \"redirect_uri\": \"https://talk-me.ru/knowledge_base\"\n" +
            "}";

    public static String answerWithNewAccessToken = "{\n" +
            "\"token_type\": \"Bearer\",\n" +
            "\"expires_in\": 86400,\n" +
            "\"access_token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijg0MTk1NTQ4ZWYxOGZkN2NlYzlmNDNlNTRlNWQxMzVhNmYyMjJjYTZlMTI0MjM0ZmI1ODg2ZGY4YzIyZTUxM2E4MDkxYzkyZjdiNTg0NDBlIn0.eyJhdWQiOiI2MWZkYjdhYS01YmNjLTQ0YTgtYjY5Ni1jZDVhMDJlNDRiOWQiLCJqdGkiOiI4NDE5NTU0OGVmMThmZDdjZWM5ZjQzZTU0ZTVkMTM1YTZmMjIyY2E2ZTEyNDIzNGZiNTg4NmRmOGMyMmU1MTNhODA5MWM5MmY3YjU4NDQwZSIsImlhdCI6MTU5Njg4OTQxMSwibmJmIjoxNTk2ODg5NDExLCJleHAiOjE1OTY5NzU4MTEsInN1YiI6IjYyMTQzNTQiLCJhY2NvdW50X2lkIjoyODk4NTI4Nywic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.sOSpIfy5ooD1g1uzXTPFbyDR4zrFnuO4-3awvpAA9_Ngap16prAEMuRZpD-3CHQYQL1T-u0UKFgDupopIWJonCI8uu1piTRF4ks1aXlNw6SvCp8JgHbF7T1cVoBbAXqpJFNL7_NmispQg0xXYhHeU3nYhRW5fdRA78Cxt6Jrz8DhP6X6KkOcHTc7z2lerBvtjGAAfckCz2-9PYbOljY8dujsch1Z7ZpAhuZZBBao5mOdFyKHQmh5h1sjTZIN0PzhA7w5MFX7A0ygCN3dvGWiqThSOvJ_RMqWjlEA9kZNKdNoGHJTF9sHD2Vp_oNbYnyHEgAKdbH4yi3BKNUY7TWxOg\",\n" +
            "\"refresh_token\": \"def50200917dfdd5889933e5f5fc466603efe56d5fd08fb41074c48aee9b00ba14627c8f9781830d734b4419eec247c833f1e96cc25dd6d0812a34a817e3cc48841284b3b9f57b92fb88f4718f28d75297553d6d99387f082f5ac78f9c7afdc8e829ecc0e94b6088c9588d86330e65bf0e65ba0e06b2abcfe1b7e22fdbc8675bedd8e37ac2596baa283c75ababfc816f050eb0c269440b2a358377683765df21edcf41d8f4ba77e1f5afc22016af8e09d65a180c4f2483a55423d9b800dc155a8a904c7e11f4015ccdc45fc5a45b27c89259da68996c00dac0730fb897c9ee3c80f372b9c71de8429f62fc2039c6eb0d3855e09a5d3417c2a51c409728da07a65769a5ddfcb21053de6b4d07f2621aac33a9e11a1b6d7962dbe1973ebd926d1c5147527d32569643d185555cc95b9857845dd22e49296aae90c6b0a1640c98d1dc67c5fa2c553cbfe655e37818ab7fee27e7d5862132d5cc49da612f6cf336ebf3c4854d7b8af9bdf06dcbaef82da1cd11468a11feb103f578160eebb1e74f89b25b0ab2ff8665ae560a1403c9b5760ae7bdffe1dbc503e0b98acd69f283864d3b88ec14f59d2629c876ed1eb6cb8ecfe71306a8\"\n" +
            "}";

    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijg0MTk1NTQ4ZWYxOGZkN2NlYzlmNDNlNTRlNWQxMzVhNmYyMjJjYTZlMTI0MjM0ZmI1ODg2ZGY4YzIyZTUxM2E4MDkxYzkyZjdiNTg0NDBlIn0.eyJhdWQiOiI2MWZkYjdhYS01YmNjLTQ0YTgtYjY5Ni1jZDVhMDJlNDRiOWQiLCJqdGkiOiI4NDE5NTU0OGVmMThmZDdjZWM5ZjQzZTU0ZTVkMTM1YTZmMjIyY2E2ZTEyNDIzNGZiNTg4NmRmOGMyMmU1MTNhODA5MWM5MmY3YjU4NDQwZSIsImlhdCI6MTU5Njg4OTQxMSwibmJmIjoxNTk2ODg5NDExLCJleHAiOjE1OTY5NzU4MTEsInN1YiI6IjYyMTQzNTQiLCJhY2NvdW50X2lkIjoyODk4NTI4Nywic2NvcGVzIjpbInB1c2hfbm90aWZpY2F0aW9ucyIsImNybSIsIm5vdGlmaWNhdGlvbnMiXX0.sOSpIfy5ooD1g1uzXTPFbyDR4zrFnuO4-3awvpAA9_Ngap16prAEMuRZpD-3CHQYQL1T-u0UKFgDupopIWJonCI8uu1piTRF4ks1aXlNw6SvCp8JgHbF7T1cVoBbAXqpJFNL7_NmispQg0xXYhHeU3nYhRW5fdRA78Cxt6Jrz8DhP6X6KkOcHTc7z2lerBvtjGAAfckCz2-9PYbOljY8dujsch1Z7ZpAhuZZBBao5mOdFyKHQmh5h1sjTZIN0PzhA7w5MFX7A0ygCN3dvGWiqThSOvJ_RMqWjlEA9kZNKdNoGHJTF9sHD2Vp_oNbYnyHEgAKdbH4yi3BKNUY7TWxOg";

}
