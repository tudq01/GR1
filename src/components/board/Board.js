import React, { useEffect, useState } from "react";
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
  const [player, setPlayer] = useState({
    x: 7,
    y: 4,
  });
  const [state, setState] = useState(maze);
  useEffect(() => {
    console.log(player)
    document.addEventListener("keydown", handleKeyDown);
    return () => {
      document.removeEventListener("keydown", handleKeyDown);
    };
  }, [player]);
  const handleKeyDown = (event) => {
    console.log(event.keyCode);

    switch (event.keyCode) {
      case 37: // Left arrow
        if (player.y > 0 && state[player.x][player.y - 1] === 0) {
          state[player.x][player.y] = 0;
          state[player.x][player.y - 1] = 3;
          console.log(state);
          setState([...state]);
          setPlayer({ ...player, y: player.y - 1 });
        }
        
        break;
      case 38: // Up arrow
        if (player.x > 0 && state[player.x - 1][player.y] === 0) {
          state[player.x][player.y] = 0;
          state[player.x - 1][player.y] = 3;
          console.log(state);
          setState([...state]);
          setPlayer({ ...player, x: player.x - 1 });
        }
        break;
      case 39: // Right arrow
        if (
          player.y < state.length - 1 &&
          state[player.x][player.y + 1] === 0
        ) {
          state[player.x][player.y] = 0;
          state[player.x][player.y + 1] = 3;
          console.log(state);
          setState([...state]);
          setPlayer({ ...player, y: player.y + 1 });
        }
        if (player.y > 0 && state[player.x][player.y + 1] === 2) {
          state[player.x][player.y] = 0;
          state[player.x][player.y + 1] = 3;
          console.log(state);
          setState([...state]);
          setPlayer({ ...player, y: player.y + 1 });
        }
        break;
      case 40: // Down arrow
        if (
          player.x < state.length - 1 &&
          state[player.x + 1][player.y] === 0
        ) {
          state[player.x][player.y] = 0;
          state[player.x + 1][player.y] = 3;
          console.log(state);
          setState([...state]);
          setPlayer({ ...player, x: player.x + 1 });
        }
        break;
      default:
        break;
    }
  };
  return (
   
      <div className={` h-3/4 w-3/4 mt-6 grid grid-cols-6 grid-rows-8`}>
        {state.map((rows, index) => {
          return (
            <>
              {rows.map((col, index) => {
                if (col === 0)
                  return (
                    <div
                      key={index}
                      className="border-2  aspect-w-1 aspect-h-1 flex justify-center items-center"
                    ></div>
                  );
                if (col === 1)
                  return (
                    <div
                      key={index}
                      className="border-2  aspect-w-1 aspect-h-1 bg-sky-600"
                    ></div>
                  );
                if (col === 2)
                  return (
                    <div
                      key={index}
                      className={`border-2 aspect-w-1  aspect-h-1 flex items-center justify-center `}
                    >
                      <img
                        src={diamond}
                        alt="diamond"
                        className="object-cover w-9 h-9"
                      ></img>
                    </div>
                  );
                if (col === 3)
                  return (
                    <div
                      key={index}
                      className="border-2  aspect-w-1 aspect-h-1 flex items-center justify-center"
                    >
                      <img
                        src={mario}
                        alt="diamond"
                        className="object-cover w-9 h-9"
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
