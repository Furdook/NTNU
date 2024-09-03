import noteslogo from '../assets/NOTES.svg'

export default function Home() {
    return(
    <div className="ml-96 mr-32 w-4/5 flex flex-col justify-center text-center selection:align-middle items-center h-full text-neutral-500">
        <img src={noteslogo} alt="notes logo" className='h-12 mb-6 opacity-30' />
        <h1>Welcome to NOTES <br /> Press &rsquo;New Note&rsquo; to create a new note</h1>
    </div>
    )
}