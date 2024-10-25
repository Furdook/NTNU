/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        primary: '#FFFFFF',
        heading: '#2d3238',
        paragraph: '#000000',
        lable: 'rgb(108, 111, 115)',
        link: '#2595ff',
        'link-hover': '#006fd8',
        border: 'rgb(0, 0, 0, 0.15)',
        'border-focus': '#a5d3ff',
        button: '#5cb85c',
        'button-hover': 'rgb(79, 172, 79)',
        'input-text': '#495057',
        'button-border': '#449d44',
      },
      borderRadius: {
        md: '.25rem',
      },
    },
  },
  plugins: [],
}
