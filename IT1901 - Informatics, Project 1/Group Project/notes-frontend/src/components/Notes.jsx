import { useEffect, useState } from 'react';
import inscribe from '../assets/inscription.png';
import sidebarclose from '../assets/hide-sidepanel.png';
import trash from '../assets/Trash.svg';
import Type from '../assets/Type.svg'
import Tag from '../assets/Tag.svg';
import noteslogo from '../assets/NOTES.svg'
import { getNotes, putNote, postNote, delNote } from '../services/api';

// eslint-disable-next-line react/prop-types
export default function Notes() {
  const [isOpen, setIsOpen] = useState(true);
  const [notes, setNotes] = useState([]);
  const [filteredNotes, setFilteredNotes] = useState([]);
  const [selectedNote, setSelectedNote] = useState(null);
  const [note, setNote] = useState({
    id: '',
    title: '',
    tags: '',
    text: '',
  });
  const [searchValue, setSearchValue] = useState("");
  const [currentTag, setCurrentTag] = useState("");
  const [showLanding, setShowLanding] = useState(false);

  useEffect(() => {
    // Get notes from server
    getNotes()
      .then((response) => {
        setNotes(response.data);
      })
      .catch((error) => {
        console.error('Error fetching notes:', error);
      });
  }, []); // only on mount


  useEffect(() => {
    if(notes.length > 0) {
      console.log("Initialized")}
    else { setShowLanding(true);}}, [notes]);
  
    const handleTagInput = (event) => {
      const value = event.target.value;
      setCurrentTag(value);
    
      if (value.endsWith(',')) {
        // Trim the comma
        const tag = value.slice(0, -1);
    
        // Find the index of the selected note in the 'notes' array
        const noteIndex = notes.findIndex((n) => n.id === selectedNote.id);
    
        if (noteIndex !== -1) {
          const updatedNotes = [...notes];
          const updatedNote = { ...updatedNotes[noteIndex] };
    
          // Add the tag to the selected note
          updatedNote.tags = updatedNote.tags
            ? `${updatedNote.tags},${tag}`
            : tag;
    
          // Update the 'notes' array with the modified note
          updatedNotes[noteIndex] = updatedNote;
    
          // Update the 'note' state with the modified note
          setNotes(updatedNotes);
          setSelectedNote(updatedNote);
          setNote(updatedNote);

          // Update server
          postNote(updatedNote.id, `${updatedNote.title}&${updatedNote.text}&${tagsAsString(updatedNote.tags)}`);
        }
    
        setCurrentTag("");
        console.log("Tag added: " + tag);
      }
    };
    
    const handleTagDelete = (event) => {
      if (selectedNote.tags) {
        const tagsArray = selectedNote.tags.split(',');
    
        if (tagsArray.length > 0) {
          tagsArray.pop(); // Remove the last tag
    
          const updatedNote = { ...selectedNote };
          updatedNote.tags = tagsArray.join(',');
    
          // Update the 'notes' array with the modified note
          const updatedNotes = notes.map((note) =>
            note.id === selectedNote.id ? updatedNote : note
          );
    
          // Update the 'note' state with the modified note
          setNotes(updatedNotes);
          setSelectedNote(updatedNote);
          setNote(updatedNote);
          setCurrentTag('');
        }
      }
      console.log("Tag deleted.");
    };
    

  const handleTagClick = (tag) => {
    console.log("Tag clicked: " + tag);
    setSearchValue(tag);
  };
  

  const handleInputChange = (event) => {
    const { name, value } = event.target;
  
    // Handle other fields
    const updatedSelectedNote = { ...selectedNote, [name]: value };
  
    if (name === "title" && value.trim() === "") {
      updatedSelectedNote.title = "Untitled note";
    }
 
    setSelectedNote(updatedSelectedNote);
    setNote(updatedSelectedNote);

    // Save the updated note to your 'notes' state
    const noteIndex = notes.findIndex((n) => n.id === updatedSelectedNote.id);
    if (noteIndex !== -1) {
      const updatedNotes = [...notes];
      updatedNotes[noteIndex] = updatedSelectedNote;
      setNotes(updatedNotes);
    }  

    // Update server
    postNote(updatedSelectedNote.id, `${updatedSelectedNote.title}&${updatedSelectedNote.text}&${tagsAsString(updatedSelectedNote.tags)}`);

  };
  
  const handleSearch = (event) => {
    const value = event.target.value;
    setSearchValue(value);
  
    if (value === "") {
      setFilteredNotes(notes);
      return;
    }
  
    const filteredNotes = notes.filter((note) => {
      const titleContainsSearch = note.title.toLowerCase().includes(value.toLowerCase());
      const contentContainsSearch = note.text.toLowerCase().includes(value.toLowerCase());
      const tagsContainsSearch = tagsAsString(note.tags).toLowerCase().includes(value.toLowerCase());
  
      return titleContainsSearch || contentContainsSearch || tagsContainsSearch;
    });
  
    setFilteredNotes(filteredNotes);

    if (value === "") {
      setFilteredNotes([]);
      setSearchValue("");
    }
  };
  
  
  
  const toggleSidebar = () => {
    setIsOpen(!isOpen);
  }

  const newNote = () => {
    let title = 'New Note ' + (notes.length + 1);
    const newNote = {
      title: title,
      tags: '',
      text: '',
    };
  
    // Add to server
    putNote(`${title}&&`)
      .then((response) => {
        newNote.id = response.data;
        console.log("New note created with ID: " + newNote.id + ".");
      });
  
    setNotes([...notes, newNote]);
    setSelectedNote(newNote);
    selectNote(newNote);
    setShowLanding(false);
  
    // Reset filtered notes to the entire list
    setFilteredNotes([...notes]);
  
    // Clear search value
    setSearchValue("");
  };
  
  

  const deleteNote = (index, note) => {
    const updatedNotes = [...notes];
    if (selectedNote.id === updatedNotes[index].id) {
      setShowLanding(true);
    }
    updatedNotes.splice(index, 1);
    console.log("Note with ID: " + note.id + " deleted.");
    setNotes(updatedNotes);

    // Delete on server
    delNote(note.id);
  };
  

  const selectNote = (selectedNote) => {
    setSelectedNote(selectedNote);
    // Set the 'note' state to match the selected note
    setNote(selectedNote);
    setShowLanding(false);
    console.log("Note with ID: " + selectedNote.id + " selected.")
    console.log("Note title: " + selectedNote.title + ".")
    console.log("Note content: " + selectedNote.text + ".")
    console.log("Note tags: " + selectedNote.tags + ".")

  }

  const notesList = (filteredNotes.length > 0 ? filteredNotes : notes).map((item, index) => (
    <li key={item.id} className="note-item flex">
      <div className="note-container flex w-full p-3 gap-2 justify-between">
        <button onClick={() => selectNote(item)} className="notebutton">
          {item.title}
        </button>
        <button
          id="delete-button"
          onClick={() => deleteNote(index, item)}
          className="rounded-md h-12 w-12 p-3 flex justify-center items-center delete-button"
          title="Delete Note"
        >
          <img src={trash} alt="" />
        </button>
      </div>
    </li>
  ));

  const sidebarStyle = {
    transform: isOpen ? 'translateX(0)' : 'translateX(-100%)',
  };

  const tagsAsList = (tags) => {
    // (tags instanceof String) did not work
    let s = "string";
    if (typeof tags == typeof s){
      return tags.split(',');
    }
    return tags
  }

  const tagsAsString = (tags) => {
    if (Array.isArray(tags)){
      let string = "";
      for (let i = 0; i < tags.length; i++){
        string+=tags[i];
        if (i < tags.length - 1) {
          string += ',';
        }
      }
      return string
    }
    return tags
  }

  return (
    <>
      <button onClick={toggleSidebar} className="fixed mt-20 ml-12 border rounded-md h-12 w-12 p-3 flex justify-center items-center">
        <img src={sidebarclose} alt="Close Sidebar" />
      </button>
      <div style={sidebarStyle} className="fixed sidebar-slide block h-full w-72 bg-neutral-800">
        <ul>
          <li>
            <div className="m-0 p-0 flex justify-center w-full">
              <input
                id="search"
                className="outline-none bg-left bg-auto w-full mx-3.5 h-12 mt-5 p-3 rounded-md bg-neutral-800"
                type="text"
                placeholder="Search..."
                value={searchValue}
                onChange={handleSearch}
              />
            </div>
          </li>
          <li className="mb-5 mt-2">
            <div className="flex justify-between gap-2">
              <button onClick={newNote} className="border rounded-md h-12 w-52 text-left pl-3.5 flex justify-start items-center gap-2">
                <img src={inscribe} alt="Inscribe" />
                New Note
              </button>
              <button onClick={toggleSidebar} className="border rounded-md h-12 w-12 p-3 flex justify-center items-center">
                <img src={sidebarclose} alt="Close Sidebar" />
              </button>
            </div>
          </li>
          {notesList}
        </ul>
      </div>
      {showLanding && (
        <div className="ml-96 mr-32 w-4/5 flex flex-col justify-center text-center selection:align-middle items-center h-full text-neutral-500">
          <img src={noteslogo} alt="notes logo" className="h-12 mb-6 opacity-30" />
          <h1>Welcome to NOTES <br /> Press &rsquo;New Note&rsquo; to create a new note</h1>
        </div>
      )}
      {!showLanding && (
        <div className="ml-96 mr-32 w-4/5 flex justify-center h-full">
          <div className="h-full w-full max-w-2xl">
            <section className="flex items-center gap-2 h-24 -mb-8 w-full">
              <label htmlFor="title">
                <img src={Type} className="-ml-1" alt="Title symbol" />
              </label>
              <input
                type="text"
                name="title"
                placeholder="Title"
                className="w-full text-2xl outline-none font-bold bg-transparent"
                value={note.title === "Untitled note" ? "" : note.title}
                onChange={handleInputChange}
              />
            </section>
            <section className="flex items-center gap-3 h-12 w-full">
              <label htmlFor="tags">
                <img src={Tag} alt="Tag symbol" />
              </label>
              <div className="tags-container flex gap-2 w-full">
                {note.tags && (
                  <div className='flex gap-2'>
                    {Array.from(tagsAsList(note.tags)).map((tag, index) => (
                      <button
                        key={index}
                        onClick={() => handleTagClick(tag)}
                        className="tag-button flex justify-center items-center bg-cyan-950 outline outline-2 outline-cyan-500 rounded-full px-2 text-xs font-bold hover-bg-cyan-500"
                      >
                        {tag}
                      </button>
                    ))
                  }
                </div>)
                }
                <input
                  type="text"
                  name="tags"
                  placeholder={"Add tags. Separate multiple tags with ',' "}
                  className="w-full bg-transparent outline-none"
                  value={currentTag}
                  onChange={handleTagInput}
                  onKeyDown={(e) => {
                    if (e.key === 'Backspace' && currentTag === '' && note.tags) {
                      handleTagDelete();
                    }
                  }}
                />
              </div>
            </section>
            <hr />
            <label htmlFor="text"></label>
            <textarea
              name="text"
              cols="30"
              rows="10"
              placeholder="Type your note here..."
              className="block w-full h-full mt-3 outline-none bg-transparent rounded-md resize-none"
              value={note.text}
              onChange={handleInputChange}
            />
          </div>
        </div>
      )}
    </>
  );
}
