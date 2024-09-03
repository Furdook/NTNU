package server;

import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest
class ServerApplicationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testHelloWorld() throws Exception {
        this.mockMvc.perform(get("/test")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @Test
    void testCreateNote() throws Exception {
        this.mockMvc.perform(put("/addNote").content("TestNoteTitle&TestNoteContent&Tag1,Tag2,Tag3")).andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/getNotes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestNoteTitle")));

        this.mockMvc.perform(delete("/deleteNote/1")).andDo(print()).andExpect(status().isOk());
                
        // Test for Json object as string, received from react
        this.mockMvc.perform(put("/addNote").content("{\"noteData\":\"TestNoteTitle&TestNoteContent&Tag1,Tag2,Tag3\"}")).andDo(print())
                        .andExpect(status().isOk());
        this.mockMvc.perform(delete("/deleteNote/1")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testEditNote() throws Exception {
        this.mockMvc.perform(put("/addNote").content("TestNoteTitle&TestNoteContent&Tag1,Tag2,Tag3")).andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(post("/editNote/1").content("TestNoteTitleEdited&TestNoteContent&Tag1,Tag2,Tag3")).andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/getNotes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestNoteTitleEdited")));

        // Test for Json object as string, received from react
        this.mockMvc.perform(post("/editNote/1").content("{\"noteData\":\"TestNoteTitle&TestNoteContent&Tag1,Tag2,Tag3\"}")).andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/deleteNote/1")).andDo(print()).andExpect(status().isOk());
    }

}
