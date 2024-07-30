package ifba.gsort.partohumano.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    
    private final String BASE_LOGIN_URL= "/auth/login";
    private final String LIST_USERS_URL= "/users";
    private final String CREATE_USERS_URL= "/users/cadastrar";
    private final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private String createLoginBody(String email, String password){
        return String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password);
    }

    private String createnewUserBody(String email, String password, String cpf, String nome, String telefone, String permissao, int codMunicipio){
        return String.format("{\"credenciais\": {\"email\": \"%s\", \"password\": \"%s\"}, \"dadosCadastrais\": {\"cpf\": \"%s\", \"nome\": \"%s\", \"telefone\": \"%s\"}, \"permissao\": \"%s\", \"codigoDoMunicipio\": %d}", email, password, cpf, nome, telefone, permissao, codMunicipio);
    }

    private void loginAdm() throws Exception{
        String body = createLoginBody("gsort", "gunsorted");

        mockMvc.perform(
                post(BASE_LOGIN_URL)
                .contentType(CONTENT_TYPE)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE));
    }

    private void loginNurse() throws Exception{
        String body = createLoginBody("Enfermeira", "EnfermagemPorAmor");

        mockMvc.perform(
                post(BASE_LOGIN_URL)
                .contentType(CONTENT_TYPE)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE));
    }


    @Test
    public void testLoginAdm() throws Exception{
        loginAdm();
    }


    @Test
    public void testLoginNurse() throws Exception{
        loginNurse();
    }

    @Test
    public void testLoginUserPezao() throws Exception{
        String body = createLoginBody("Lavagem", "voceMeDaAcademias");

        mockMvc.perform(
                post(BASE_LOGIN_URL)
                .contentType(CONTENT_TYPE)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    public void testLoginUserCabral() throws Exception{
        String body = createLoginBody("Influencia", "Trafico");

        mockMvc.perform(
                post(BASE_LOGIN_URL)
                .contentType(CONTENT_TYPE)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    public void testListUsersLoggedAsNurse() throws Exception{
        String body = createLoginBody("Enfermeira", "EnfermagemPorAmor");

        mockMvc.perform(
                post(BASE_LOGIN_URL)
                .contentType(CONTENT_TYPE)
                .content(body))
            .andExpect(status().isOk())
            .andExpect(content().contentType(CONTENT_TYPE));

        mockMvc.perform(
                get(LIST_USERS_URL))
            .andExpect(status().isForbidden());
    }

    // @Test
    // public void testListUsersLoggedAsAdm() throws Exception{
    //     loginAdm();

    //     mockMvc.perform(
    //             get(LIST_USERS_URL))
    //         .andExpect(status().isOk())
    //         .andExpect(content().contentType(CONTENT_TYPE));
    // }

    // @Test
    // public void testListUsersLoggedAsNurse() throws Exception{
    //     loginNurse();

    //     mockMvc.perform(
    //             get(LIST_USERS_URL))
    //         .andExpect(status().isForbidden())
    //         .andExpect(content().contentType(CONTENT_TYPE));
    // }

    // @Test
    // public void testCreateUser() throws Exception{
    //     String body = createnewUserBody("user2", "gunsorted", "0671", "Mastheus", "71", "Enfermeira", 2927408);

    //     mockMvc.perform(
    //             post(CREATE_USERS_URL)
    //             .contentType(CONTENT_TYPE)
    //             .content(body))
    //         .andExpect(status().isOk());    
    // }

}
