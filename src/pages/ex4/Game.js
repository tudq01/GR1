import React from "react";
import Board from "../../components/board/Board";
function Game() {
  return (
    <div className="w-full  flex justify-center min-h-screen">
      <div className="flex flex-col gap-1 mt-10  p-8  w-3/4 items-center">
        <h2 className="text-2xl text-center font-bold">Draw Map</h2>

        <Board />
        <button className="rounded px-3 py-2 text-white w-1/3 bg-green-500 mt-3">
          Run
        </button>
      </div>
    </div>
  );
}

export default Game;
