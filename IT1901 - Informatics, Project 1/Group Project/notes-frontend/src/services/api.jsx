import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Servers URL

export const getNotes = () => {
  return axios.get(`${API_BASE_URL}/getNotes`);
};

// noteData: string with title, context, tags. Separated with &
export const putNote = (noteData) => {
  return axios.put(`${API_BASE_URL}/addNote`,{noteData}); //return id
};

// noteData: string with title, context, tags. Separated with &
export const postNote = (id, noteData) => {
    return axios.post(`${API_BASE_URL}/editNote/${id}`, {noteData});
};

export const delNote = (id) => {
    return axios.delete(`${API_BASE_URL}/deleteNote/${id}`)
}