import "./styles/Paginator.css";

interface PaginatorProps {
  pageCount: number;
  currentPage: number;
  onPageChange: (pageNumber: number) => void;
}

function Paginator({ pageCount, currentPage, onPageChange }: PaginatorProps) {
  const createPages = () => {
    if (pageCount <= 4) {
      return (
        <section>
          {[...Array(pageCount)].map((_, index) => {
            const pageNumber = index + 1;
            const isActive = pageNumber === currentPage;
            return (
              <button
                key={index}
                className={`page ${isActive ? "active" : ""}`}
                onClick={() => onPageChange(pageNumber)}
              >
                {pageNumber.toString()}
              </button>
            );
          })}
        </section>
      );
    } else {
      return (
        <section>
          {[...Array(2)].map((_, index) => {
            const pageNumber = index + 1;
            const isActive = pageNumber === currentPage;
            return (
              <button
                key={index}
                className={`page ${isActive ? "active" : ""}`}
                onClick={() => onPageChange(pageNumber)}
              >
                {pageNumber.toString()}
              </button>
            );
          })}
          <button className="page">...</button>
          <button className="page" onClick={() => onPageChange(pageCount)}>
            {pageCount.toString()}
          </button>
        </section>
      );
    }
  };

  return (
    <section id="paginator">
      <button
        id="back"
        onClick={() => {
          if (currentPage > 1) {
            onPageChange(currentPage - 1);
          }
        }}
      >
        Forrige
      </button>
      {createPages()}
      <button
        id="forward"
        onClick={() => {
          if (currentPage < pageCount) {
            onPageChange(currentPage + 1);
          }
        }}
      >
        Neste
      </button>
    </section>
  );
}

export default Paginator;
