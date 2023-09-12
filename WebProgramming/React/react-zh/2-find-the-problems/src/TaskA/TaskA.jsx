import { useState } from "react";
import { Time } from "./Time";

const initialTime = {
  hour: 15,
  minute: 0,
  second: 0,
};

export const TaskA = () => {
  const [clock, setClock] = useState({
    showSeconds: true,
    time: initialTime,
  });

  const handleToggleShowSecond = () => {
    setClock({
      ...clock,
      showSeconds: !clock.showSeconds,
    });
  };

  const handleAddFiveMinutes = () => {
    const increasedMinute = clock.time.minute + 5;
    const newMinute = increasedMinute % 60;
    const extraHour = Math.floor(increasedMinute / 60);

    clock.time.hour += extraHour;
    clock.time.minute = newMinute;

    setClock({
      showSeconds: clock.showSeconds,
      time: {
        hour: clock.time.hour,
        minute: clock.time.minute,
        second: 0
      }
    })
    initialTime.minute = 0;
  };

  return (
    <div>
      <h1>Task A</h1>

      <h2>Initial time</h2>
      <p>
        <Time showSeconds={true} time={initialTime}></Time>
      </p>

      <h2>Modified time</h2>
      <p>
        <Time showSeconds={clock.showSeconds} time={clock.time}></Time>
        <br />
        <button onClick={handleToggleShowSecond}>Toggle show seconds</button>
        <button onClick={handleAddFiveMinutes}>+5 minutes</button>
      </p>
    </div>
  );
};
