import React from 'react';
import ReactDOM from 'react-dom/client';
import Notes from './components/Notes';

import './index.css';

function App() {

  return (
    <React.StrictMode>
      <main className='bg-neutral-900'>
        <Notes/>
      </main>
    </React.StrictMode>
  );
}

export default App;
