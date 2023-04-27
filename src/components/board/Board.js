import React, { useState } from "react";
import { Row, Col, Grid, Card } from "antd";
import "../../index.css";
import mario from "../../asset/mario.png";
import diamond from "../../asset/diamond.png";

function Board() {
  const rowNum = 8;
  const colNum = 6;
  // 0: no 1 obs 2 diamond  3: mario
  const maze = [
    [0, 0, 0, 0, 2, 0],
    [0, 0, 0, 1, 1, 1],
    [0, 0, 0, 0, 0, 0],
    [1, 1, 1, 1, 0, 0],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 1, 1, 1, 1],
    [0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 3, 0],
  ];
  const [state, setState] = useState(maze);

  return (
    <div className={` h-3/4 mt-6 grid grid-cols-${colNum} grid-rows-${rowNum}`}>
      {state.map((rows, index) => {
        return (
          <>
            {rows.map((col, index) => {
              if (col === 0)
                return (
                  <div
                    key={index}
                    className="border-2 w-10 aspect-w-1 aspect-h-1 flex justify-center items-center"
                  ></div>
                );
              if (col === 1)
                return (
                  <div
                    key={index}
                    className="border-2 w-10 aspect-w-1 aspect-h-1 bg-sky-600"
                  ></div>
                );
              if (col === 2)
                return (
                  <div
                    key={index}
                    className={`border-2 aspect-w-1 w-10 aspect-h-1  `}
                  >
                    <img
                      src={diamond}
                      alt="diamond"
                      className="object-cover"
                    ></img>
                  </div>
                );
              if (col === 3)
                return (
                  <div
                    key={index}
                    className="border-2 w-10 aspect-w-1 aspect-h-1 flex items-center justify-center"
                  >
                    <img
                      src={mario}
                      alt="diamond"
                      className="object-cover"
                    ></img>
                  </div>
                );
            })}
          </>
        );
      })}
    </div>
  );
}

export default Board;
